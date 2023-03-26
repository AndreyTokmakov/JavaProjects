/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Condition_Simple_Tests.java class
*
* @name    : Condition_Simple_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Mar 7, 2021
****************************************************************************/

package Condition;

import java.util.Date;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


final class ReentrantLockWithCondition {

    private final Stack<String> stack = new Stack<>();
    private final static int CAPACITY = 5;

    private ReentrantLock lock = new ReentrantLock();
    private Condition stackEmptyCondition = lock.newCondition();
    private Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item) throws InterruptedException{
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() throws InterruptedException {
        try {
            lock.lock();
            while(stack.size() == 0) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}


public class Condition_Tests_2 {

	public static void Test1() throws InterruptedException, ExecutionException {
		String someText = "Some_Texs";
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> { 
			System.out.println(new Date() + ": Task stated. Local variable value = " + someText);
			sleep(2000);
			System.out.println(new Date() + ": Task completed. Local variable value = " + someText);
		});
		// future.get();
	}
	
	public static void Test_Simple_UNIMPLEMENTED() throws InterruptedException, ExecutionException 
	{
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		ExecutorService executor  = Executors.newFixedThreadPool(5);
		
		executor.submit(() -> {
			String msg = null;
			try {
				lock.lock();
				System.out.println(new Date() + ": Consumer. Waiting for message");
				
				lock.wait(1000);
				System.out.println(new Date() + ": Consumer. Donee");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
	            lock.unlock();
	        }
			
			System.out.println(new Date() + ": Consumer. Message received = " + msg);
		});
		
		executor.submit(() -> {
			sleep(2000);
			
			lock.lock();
			condition.signalAll();
			System.out.println(new Date() + ": Producer: message pushed");
			lock.unlock();
		});
	}
	
	public static void Test_Push_To_Stack () throws InterruptedException, ExecutionException 
	{
		ReentrantLockWithCondition stack = new ReentrantLockWithCondition();
		ExecutorService executor  = Executors.newFixedThreadPool(5);
		
		executor.submit(() -> {
			String msg = null;
			try {
				System.out.println(new Date() + ": Consumer. Waiting for message");
				msg = stack.popFromStack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(new Date() + ": Consumer. Message received = " + msg);
		});
		
		executor.submit(() -> {
			sleep(2000);
			try {
				stack.pushToStack("SOME_TEXT_MESSAGE");
				System.out.println(new Date() + ": Producer: message pushed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(new Date() + "*** We are here ***");
	}
	
	private static void sleep(int msSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(msSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Test1();
		
		 Test_Push_To_Stack();
		
		// Test_Simple_UNIMPLEMENTED();
	}
}
