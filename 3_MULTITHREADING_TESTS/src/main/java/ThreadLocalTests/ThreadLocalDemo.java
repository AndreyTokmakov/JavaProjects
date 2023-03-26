/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ThreadLocal demo class
*
* @name    : ThreadLocalDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 08, 2020
****************************************************************************/ 

package ThreadLocalTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Task implements Runnable {
	// Atomic integer containing the next thread ID to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(0);
    
	// Thread local variable containing each thread's ID
	private static final ThreadLocal<Integer> threadId = 
		new ThreadLocal<Integer>() {
			@Override
			protected Integer initialValue() {
				return nextId.getAndIncrement();
			}
		};
   
	// Returns the current thread's unique ID, assigning it if necessary
	public int getThreadId()  {
		return threadId.get();
	}
   
	// Returns the current thread's starting timestamp
	private static final ThreadLocal<Date> startDate = 
		new ThreadLocal<Date>() {
			@Override
			protected Date initialValue() {
				return new Date();
			}
		};
 
	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
		try {
		   TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", getThreadId(), startDate.get());
	}
}

public class ThreadLocalDemo {
	public static void main(String[] args) throws InterruptedException 
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 4; i++) {
			threads.add(new Thread(new Task()));
			threads.get(threads.size() -1).start();
			TimeUnit.SECONDS.sleep(1);
		}
		for (Thread T: threads)
			T.join();
	}
}
