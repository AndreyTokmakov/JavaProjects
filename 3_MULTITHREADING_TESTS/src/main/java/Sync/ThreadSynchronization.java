package Sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Printer {
	public void printCount() {
		try {
			for (int i = 5; i > 0; i--) 
				System.out.println("Counter   ---   "  + i );
		} catch (final Exception exc) {
			System.out.println("Thread  interrupted.");
		}
	}
}

class ThreadDemo extends Thread {
	/** Thread handler. **/
	protected Thread thread;
	/** Thread name. **/
	protected String threadName;
	/** Printer. **/
	protected Printer printer;

	public ThreadDemo(final String name, 
					  final Printer printer) {
		this.threadName = name;
		this.printer = printer;
	}
	   
	public void run() {
		this.printer.printCount();
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting " +  threadName );
		if (null == thread) {
			this.thread = new Thread (this, threadName);
			this.thread.start();
		}
	}
}

class ThreadDemoSync extends ThreadDemo {
	
	public ThreadDemoSync(final String name, 
					      final Printer printer) {
		super(name, printer);
	}
	
	@Override
	public void run() {
		synchronized (this.printer) {
			this.printer.printCount();
	    }
		System.out.println("Thread " +  threadName + " exiting.");
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Tester2 {
	
	private class ThreadHandler {
		
		protected long counter = 0;
		
		public void DoWork2() {
			this.updateCounter();
		}
		
		public void DoWork_Synchronized() {
			this.updateCounter_Synchronized();
		}
		
		protected void updateCounter() {
			for (int i = 1; i <= 1000000; i++)
				this.setCounter(i);
		}
		
		protected synchronized void updateCounter_Synchronized() {
			for (int i = 1; i <= 1000000; i++)
				this.setCounter(i);
		}

		public long getCounter() {
			return counter;
		}

		public void setCounter(long counter) {
			this.counter = counter;
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	public void ThreadTest() throws InterruptedException {
		ThreadHandler handler = new ThreadHandler();
		
		System.out.println("Thread " + Thread.currentThread().getName() + " starting thread.");
		
		List<Thread> threads = new ArrayList<Thread>();
		for (int i =0; i < 10; i++) {
			threads.add(new Thread(handler::DoWork2));
			threads.get(threads.size() -1).start();
		}
		for (Thread T: threads)
			T.join();

		System.out.println("Thread " + Thread.currentThread().getName() + " done.");
		System.out.println("Counter = " + handler.getCounter());
	}
	
	
	public void ThreadTest_Synchronized() throws InterruptedException {
		ThreadHandler handler = new ThreadHandler();
		
		System.out.println("Thread " + Thread.currentThread().getName() + " starting thread.");
		
		List<Thread> threads = new ArrayList<Thread>();
		for (int i =0; i < 10; i++) {
			threads.add(new Thread(handler::DoWork_Synchronized));
			threads.get(threads.size() -1).start();
		}
		
		for (Thread T: threads)
			T.join();
		
		System.out.println("Thread " + Thread.currentThread().getName() + " done.");
		System.out.println("Counter = " + handler.getCounter());
	}
	
	
	public void ThreadTest2() {
		Printer PD = new Printer();
		ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
		ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );

		T1.start();
		T2.start();

		try {
			T1.join();
			T2.join();
		} catch ( Exception e) {
			System.out.println("Interrupted");
		}
	}
	
	public void ThreadTest2_Synchronized() {
		Printer PD = new Printer();
		ThreadDemoSync T1 = new ThreadDemoSync( "Thread - 1 ", PD );
		ThreadDemoSync T2 = new ThreadDemoSync( "Thread - 2 ", PD );

		T1.start();
		T2.start();

		try {
			T1.join();
			T2.join();
		} catch ( Exception e) {
			System.out.println("Interrupted");
		}
	}
	
	public void Synchonize_Thread_Lambda() throws InterruptedException {
		Object lock = new Object();

		Runnable task = () -> {
			synchronized (lock) {
				System.out.println("Thread started.");
				// TODO
				System.out.println("Thread done.");
			}
		};

		Thread th1 = new Thread(task);
		th1.start();
		synchronized (lock) {
			for (int i = 0; i < 8; i++) {
				Thread.currentThread();
				Thread.sleep(100);
				System.out.print("  " + i);
			}
			System.out.println(" ...");
		}
	}
}

public class ThreadSynchronization {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		Tester2 tester = new Tester2();
		
		// tester.ThreadTest();
		// tester.ThreadTest_Synchronized();
		
		// tester.ThreadTest2();
		// tester.ThreadTest2_Synchronized();
		
		tester.Synchonize_Thread_Lambda();
	}
}
