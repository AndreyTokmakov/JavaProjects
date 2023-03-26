/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* JAVA Future tests 
*
* @name    : Future.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package Runnable;

import java.util.concurrent.TimeUnit;

import Utilities.Utilities;

/** Worker class : **/
class Worker implements Runnable {
	/** **/
	private final static long SLEEP_TIMEOUT = 1000;
	/** **/
	private volatile boolean run = false;

	/** Worker class constructor: **/
	public Worker() {
	
	}

	/** Do something: **/
	@Override
	public void run() {
		this.run = true;
		while (true == run) {
			try {
				TimeUnit.MILLISECONDS.sleep(Worker.SLEEP_TIMEOUT);
				System.out.println("Working....");
			} catch (final InterruptedException exc) {
				// exc.printStackTrace();
				System.out.println(exc.getMessage());
			}
		}
		System.out.println("Thread stoped.");
	}
	
	public void stop()  {
		System.out.println("Stoping thread...");
		this.run = false;
	}
}


//============================================================================
//							RunnableTestsClass
//============================================================================

class RunnableTestsClass {
	
	protected void sleep(int timeout) {
		try {
			Thread.currentThread();
			Thread.sleep(timeout);
		} catch (final InterruptedException e) {
			// Skip this
		}
	}

	/** Implements a simple thread test using the Runnable interface. **/
	public void SimpleRunnableTest_1() {
		final Runnable task = ()-> {
			final String threadName = Thread.currentThread().getName();
			System.out.println("[" + threadName + "]: Hello");
			sleep(1000);
			System.out.println("[" + threadName + "]: Done");
		};
	
		task.run();
		
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Done!");
	}
	
	/** Implements a simple thread test using the Runnable interface. 
	 * @throws InterruptedException **/
	public void SimpleRunnableTest_2() throws InterruptedException {
		final Runnable task = ()-> {
			try {
				final String threadName = Thread.currentThread().getName();
				System.out.println("[" + threadName + "]: " + "Foo");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("[" + threadName + "]: " + "Bar");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		thread.join();
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Done!");
	}
	
	/** Implements a simple thread test using the Runnable interface. **/
	public void Simple_Infinite_Thread() {
		final Runnable task = () -> {
			final String threadName = Thread.currentThread().getName();
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(200);
					System.out.println("[" + threadName + "]: " + "Helloo");
				}  catch (final InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		};
		
		Thread thread = new Thread(task);
		
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		thread.start();
		
		Utilities.sleep(100);
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Thread started.");
	}
	
	public void RunThread_and_Join() {
		final Runnable task = () -> {
			final String threadName = Thread.currentThread().getName();
			try {
				for (int i = 1; i <= 5; i++) {
					System.out.println("[" + threadName + "]: " + i);
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (final InterruptedException exc) {
				exc.printStackTrace();
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
		Utilities.sleep(500);
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Thread started.");
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Thread completed.");
	}
}

/////////////////////////////////// TESTS ////////////////////////////////////////////

public class RunnableTests {

	@SuppressWarnings("unused")
	private void Simple_Test() throws InterruptedException {
		/** Creating the Worker thread. **/
		Worker worker = new Worker();
		Thread threadHandle = new Thread(worker);
		threadHandle.start();
		
		Utilities.sleep(5000);
		worker.stop();
		threadHandle.join();
		
	}

	/* @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		RunnableTests tests = new RunnableTests();
		// tests.Simple_Test();
		
		RunnableTestsClass tests2 = new RunnableTestsClass();
		tests2.SimpleRunnableTest_1();
		// tests2.SimpleRunnableTest_2();
		// tests2.Simple_Infinite_Thread();
		// tests2.RunThread_and_Join();
	}
}
