//============================================================================
//Name        : ThreadTester.java
//Created on  : Jan 25, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class TestObject{
	private String value = "";
	
	/**  @param value*/
	public TestObject(String value) {
		this.value = value;
	}
	/** @return the value : **/
	public String getValue() {
		return value;
	}
	/** @param value the value to set : **/
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}

class Table {
	/** **/
	//private Map<Integer,String> map = Collections.synchronizedMap(new HashMap<Integer,String>());
	private ConcurrentHashMap<Integer,TestObject> map = new ConcurrentHashMap <Integer,TestObject>(16, 0.9f, 1);
	
	/** **/
	public Table() {
		for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}
	}
	
	public void Read()
	{
		TestObject obj = map.get(new Random().nextInt(10) + 1);
		System.out.println(obj);
		try {
	    	TimeUnit.MILLISECONDS.sleep(1);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void Write()
	{
		map.clear();
		try {
	    	TimeUnit.SECONDS.sleep(10);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}
	}
	
	public void Test()
	{
		/*for (int i = 1; i <= 10; i++) {
			map.put(i, new TestObject("String" + i));
		}*/
		
		System.out.println(map.size());
	}
}


//============================================================================
//						RunnableTestsClass
//============================================================================
class RunnableTestsClass {
	
	/** Implements a simple thread test using the Runnable interface. **/
	public void SimpleRunnableTest_1() {
		final Runnable task = ()-> {
		    final String threadName = Thread.currentThread().getName();
		    System.out.println("[" + threadName + "]: " + "Hello");
		};

		task.run();

		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Done!");
	}
	
	/** Implements a simple thread test using the Runnable interface. **/
	public void SimpleRunnableTest_2() {
		final Runnable job = ()-> {
		    try {
		    	final String threadName = Thread.currentThread().getName();
			    System.out.println("[" + threadName + "]: " + "Foo");
		        TimeUnit.SECONDS.sleep(1);
			    System.out.println("[" + threadName + "]: " + "Bar");
		    }
		    catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
		
		Thread thread = new Thread(job);
		thread.start();
	}
	
	/** Implements a simple thread test using the Runnable interface. **/
	public void Simple_Infinite_Thread()
	{
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
	
	public void RunThread_and_Join()
	{
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


//============================================================================
//	Deamon tests class
//============================================================================
final class Deamons {
	
	public void createThread() {
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
		 
		Thread daemonThread = new Thread(task);
		
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		daemonThread.setDaemon(true);
		daemonThread.start();
		
		Utilities.sleep(100);
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Thread started."); 
		Utilities.sleep(60 * 1000);
	}
}


//============================================================================
//	ThreadGroup tests class
//============================================================================
final class ThreadGroupTests {
	 
	static class Task extends Thread {
		
	    public Task(final ThreadGroup threadGroup,
	    			final String name) {
	        super(threadGroup, name);
	    }
	 
	    public void run() {
	        boolean running = true;
			final String threadName = Thread.currentThread().getName();
			while (running) {
			    try {
			        TimeUnit.SECONDS.sleep(1);
				    System.out.println("[" + threadName + "]: is running");
			    }  catch (final InterruptedException exc) {
	                running = false;
	                System.out.println(getName() + " is interrupted and then terminates");
			    }
			}
	    }
	}
	
	protected void runGroup() {
		System.out.println("[" + Thread.currentThread().getName() + "]: Starting thread");
		
		ThreadGroup group = new ThreadGroup("<ThreadsGroup>");
		 
        new Task(group, "A").start();
        new Task(group, "B").start();
        new Task(group, "C").start();
        new Task(group, "D").start();
 
		System.out.println("[" + Thread.currentThread().getName() + "]: " + "Thread started."); 
		Utilities.sleep(10 * 1000);
 
        group.interrupt();
	}
}


/** @author tokmakov     **/
/** @class  ThreadTester **/
//@SuppressWarnings("unused")
public class ThreadTests {
	
	/** MAIN : 
	 * @throws InterruptedException */
	public static void main(String[] args) throws InterruptedException {
		
		RunnableTestsClass taskTests = new RunnableTestsClass();
		// taskTests.SimpleRunnableTest_1();
		// taskTests.SimpleRunnableTest_2();
		// taskTests.Simple_Infinite_Thread();
		// taskTests.RunThread_and_Join();
		
		Deamons deamonsTests = new Deamons();
		// deamonsTests.createThread();
		
		ThreadGroupTests groupTests = new ThreadGroupTests();
		// groupTests.runGroup();
	}
}
