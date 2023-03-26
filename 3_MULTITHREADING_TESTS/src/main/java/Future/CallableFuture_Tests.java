/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CallableFuture_Tests.java class
*
* @name    : CallableFuture_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 31, 2021
****************************************************************************/

package Future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class FactorialCalculator implements Callable<Integer>
{
    private Integer number;
 
    public FactorialCalculator(Integer number) {
        this.number = number;
    }
 
    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } 
        else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.println("Result for number - " + number + " -> " + result);
        return result;
    }
}


class CallableFutureTests
{
      public void Test1() 
      {
          ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
          List<Future<Integer>> resultList = new ArrayList<>();

          for (int i=0; i<4; i++)
          {
              FactorialCalculator calculator  = new FactorialCalculator(i);
              Future<Integer> result = executor.submit(calculator);
              resultList.add(result);
          }
           
          for(Future<Integer> future : resultList) {
                try {
                    System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
                }  catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //shut down the executor service now
            executor.shutdown();
      }
}

public class CallableFuture_Tests {
	private final static CallableFutureTests tests = new CallableFutureTests();
	
	public static void main(String[] args) {
		tests.Test1();
	}
}
