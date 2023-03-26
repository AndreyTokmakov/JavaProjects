/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Callable tests 
*
* @name    : Callable_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 
package Callable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/////////////////////////////////////////////////////////////////////////////////////////////
//										CallableWorker                                     //
/////////////////////////////////////////////////////////////////////////////////////////////

class CallableWorker implements Callable<String> {
	protected String threadName;
	
    public CallableWorker() {
    	this.threadName = Thread.currentThread().getName();
    }
	
	@Override
	public String call() throws Exception {
		System.out.println("[" + threadName + "] Stated at " + new Date());
		Thread.sleep(1000);
		System.out.println("[" + threadName + "] Completed at " + new Date());
		return Thread.currentThread().getName();
	}
	
	public void Test() {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<String>> list = new ArrayList<Future<String>>();
		Callable<String> callable = new CallableWorker();
		
		for (int i = 0; i < 100; i++){
			Future<String> future = executor.submit(callable);
			list.add(future);
		}
		for (final Future<String> future: list){
			try {
				System.out.println(new Date()+ "::" + future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////
//										SumTask                                            //
/////////////////////////////////////////////////////////////////////////////////////////////

class SimpleSumTask implements Callable<Integer> {
	private int num = 0;
	private String threadName;
	
    public SimpleSumTask(int num) {
    	this.num = num;
    	this.threadName = Thread.currentThread().getName();
    }
    
	@Override
	public Integer call() throws Exception {
		System.out.println("[" + threadName + "] Stated at " + new Date());
		int result = 0;
		for(int i = 1;i <= num;i++){
			result += i;
		}
		System.out.println("[" + threadName + "] Completed at " + new Date());
		return result;
	}
}

////////////////////////////////////////////// TESTS //////////////////////////////////////////////////

/** @author AndTokm. **/
public class Callable_Tests {
	
	protected void SumTaskTest() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> future = service.submit(new SimpleSumTask(20));
		Integer result = future.get();
		System.out.println(result);
	}
	
	protected void SumTaskTest2() throws InterruptedException, ExecutionException {
		SimpleSumTask callable1 = new SimpleSumTask(5);
		SimpleSumTask callable2 = new SimpleSumTask(10);
	 
		FutureTask<Integer> futureTask1 = new FutureTask<Integer>(callable1);
		FutureTask<Integer> futureTask2 = new FutureTask<Integer>(callable2);
		ExecutorService executor = Executors.newFixedThreadPool(2);
	        
		executor.execute(futureTask1);
		executor.execute(futureTask2);
		
		while (true) {
			try {
				if (futureTask1.isDone() && futureTask2.isDone()){
					System.out.println("Done");
					executor.shutdown();
					return;
				}
				
				if (false == futureTask1.isDone()){
					System.out.println("FutureTask1 isDone = " + futureTask1.get());
				}
	                 
				System.out.println("Wait for FutureTask2....");
				Integer result = futureTask2.get(200L, TimeUnit.MILLISECONDS);
				if (null != result){
					System.out.println("FutureTask2 result = " + result);
				}
			} catch (final InterruptedException | ExecutionException exc) {
				exc.printStackTrace();
			} catch (final TimeoutException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	/** main: 
	 * @throws ExecutionException 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// CallableWorker worker = new CallableWorker();
		// worker.Test();

		Callable_Tests tester = new Callable_Tests();
		// tester.SumTaskTest();
		tester.SumTaskTest2();
	}
}
