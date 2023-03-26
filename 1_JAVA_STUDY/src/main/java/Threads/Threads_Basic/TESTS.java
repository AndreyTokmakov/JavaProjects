/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Threads tests class
*
* @name      : ThreadsTests.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 19, 2020
* 
****************************************************************************/ 

package Threads.Threads_Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import Threads.Utilities;
//import threads2.CustomThread;
//import threads2.CustomThreadInterrupt;

//===================================================================================================
//										Handler class
//===================================================================================================

final class Handler {
	protected long counter = 0;
	
	public void DoWork1() {
	    try {
	    	System.out.println("Thread started");
	        TimeUnit.SECONDS.sleep(5);
	        System.out.println("Thread done");
	    } catch (final InterruptedException exc) {
	    	exc.printStackTrace();
	    }
	}
	
	public void DoWork2() {
		this.updateCounter();
	}
	
	protected void updateCounter() {
		for (int i = 1; i <= 200000; i++)
			this.setCounter(i);
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
}


/** IThreadHandler interface: **/
interface IThreadHandler extends Runnable  {
	public abstract void Stop();
}

/** CustomThread class: **/
final class CustomThread1 extends Thread  {
    private IThreadHandler test;
    
    public CustomThread1(IThreadHandler test) {
        super(test);
        this.test = test;
    }   
    
    public void Stop() {
         test.Stop();
    }
}

/** ThreadHandlerTest class: **/
final class ThreadHandlerTest implements IThreadHandler {
	private volatile boolean runTest = false;
	
	public void run()  {
        runTest = true;
        System.out.println("Thread started " + Thread.currentThread().getName());
        int i = 0;
        while (i++ < 100 && runTest) {
        	Utilities.sleep(100);
            System.out.println("Thread running " + i);
        }
        System.out.println("Thread stoped " + Thread.currentThread().getName());
    }

	public void Stop()  {
		runTest = false;
	}
}


//===================================================================================================
//                                Threads tests class
//===================================================================================================

final class Threads {
	private int counter = 0;
	
	public synchronized void increment() {
	    counter++;
	}
	
	public void Sleep(final long milliseconds) {
		try {
			Thread.currentThread();
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void ThreadTest0()
	{
		final Consumer<String> printer = value -> System.out.println(value);
		List<String> list = new ArrayList<String>();
		
		Runnable task = () -> {
		    Sleep(4000);
		    for (int i = 0; i < 5; i++)
		    	list.add("Value_" + i);
		    
		    System.out.println("Thread " + Thread.currentThread().getName() + " done.");
		};
	
		Thread thread = new Thread(task);
		thread.start();
		
		System.out.println("List before thread: ");
		list.forEach(printer);
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("List after thread: ");
		list.forEach(printer);
	}
	
	public void ThreadTest1()
	{
		Runnable task = () -> {
		    try {
		    	System.out.println("Thread started");
		    	increment();
		        TimeUnit.SECONDS.sleep(5);
		        System.out.println("counter = " + counter);
		    }  catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
		 
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(task);
			thread.start();
		}
	}
	
	public void ThreadTest4() throws InterruptedException
	{
		Handler handler = new Handler();
		System.out.println("Thread " + Thread.currentThread().getName() + " starting thread.");
		
		for (int i =0; i < 5; i++) {
			Thread thread = new Thread(handler::DoWork2);
			thread.start();
			thread.join();
		}
		
		System.out.println("Thread " + Thread.currentThread().getName() + " done.");
		System.out.println("Counter = " + handler.getCounter());
	}
	
	
	protected void ListAllThreads() {
		Set<Thread> threads = Thread.getAllStackTraces().keySet();
		 
		for (Thread t : threads) {
		    String name = t.getName();
		    Thread.State state = t.getState();
		    int priority = t.getPriority();
		    String type = t.isDaemon() ? "Daemon" : "Normal";
		    System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
		}
	}
	
	protected void Run_Custom_Threads() {
		CustomThread1 T = new CustomThread1(new ThreadHandlerTest());
        T.start();
        Utilities.sleep(3000);
        T.Stop();
        
        System.out.println("Application stoped");
	}
	
	public void CustomThread_Stop_Timeout_1() throws InterruptedException {
		CustomThread thread = new CustomThread();	
		
		thread.setTimeout(2);
		thread.start();		
		System.out.println("MAIN: Thread stated. First attempt");

		Utilities.sleep(3000);
		System.out.println("MAIN: Stopping thread");
		
		thread.Stop();
	}
	
	public void CustomThread_Stop_Timeout_2() throws InterruptedException {
		CustomThread thread = new CustomThread();	
		
		thread.setTimeout(4);
		thread.start();		
		System.out.println("MAIN: Thread stated. First attempt");

		Utilities.sleep(3000);
		System.out.println("MAIN: Stopping thread");
		
		thread.Stop();
	}
	
	
	public void CustomThread_Interrupt_Test() throws InterruptedException {
		CustomThreadInterrupt thread = new CustomThreadInterrupt();	
		
		thread.setTimeout(10);
		thread.start();		
		System.out.println("MAIN: Thread stated. First attempt");

		Utilities.sleep(3000);
		
		System.out.println("MAIN: Stopping thread");
		thread.Stop();
	}

}

public class TESTS {
	private static final Threads tests = new Threads();
	
	public static void main(String[] args) throws InterruptedException {
		// tests.ThreadTest0();
		// tests.ThreadTest1();
		// tests.ThreadTest2();
		
		// tests.ListAllThreads();
		
		// tests.Run_Custom_Threads();
		
		// tests.CustomThread_Stop_Timeout_1();
		// tests.CustomThread_Stop_Timeout_2();
		
		tests.CustomThread_Interrupt_Test();
	}
}
