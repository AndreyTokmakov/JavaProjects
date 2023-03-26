package Threads.Sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SynchronizedTable {
	/** Static SynchronizedTable class instance : **/
	private static SynchronizedTable __instance = null;

	private int counter = 0;
	
	/** getInstance : **/  
	public static SynchronizedTable getInstance() {
		if (null == __instance) {
			synchronized (SynchronizedTable.class) {
				if (null == __instance) {
					__instance = new SynchronizedTable();
				}
			}
		}
		return __instance;
	}
	
	public SynchronizedTable() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCounter() {
		return this.counter;
	}
	
    public void Increment__NoSync() {
        this.counter++;
    }

    public void Increment_Sync1() {
        synchronized (this) {
        	this.counter++;
        }
    }
    
    public synchronized void Increment_Sync2() {
    	this.counter++;
    }
}

//////////////////////////////////////////////////////////////////////

class ClassWithLock {
    private Lock lock = new ReentrantLock();
	private long counter = 0;
 
    public void IncrementWithLock() {
        lock.lock();  
        try {
        	this.counter++;
        } 
        finally {
        	lock.unlock(); 
        }
    }
    
    public void Increment_No_Lock() {
        this.counter++;
    }

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
}

//////////////////////////////////////////////////////////////////////

class Tester {
	public void Synchronized_Method_Test() 
	{
		SynchronizedTable tbl = SynchronizedTable.getInstance();
		Runnable runnable = () -> {
			for (int i = 0; i < 1000000; i++) 
			{
				// tbl.Increment__NoSync();
				 tbl.Increment_Sync1();
				// tbl.Increment_Sync2();;
			}
		};
		 
		List<Thread> threads = new ArrayList<Thread>();
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
			threads.add(thread);
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime);
		System.out.println("Counter = " + tbl.getCounter());	
	}
	
	
	public void ReentrantLock_Test() {
		ClassWithLock obj = new ClassWithLock();
		Runnable runnable = () -> {
			for (int i = 0; i < 1000000; i++) 
			{
				// obj.Increment_No_Lock();
				obj.IncrementWithLock();
			}
		};
		 
		List<Thread> threads = new ArrayList<Thread>();
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(runnable);
			thread.start();
			threads.add(thread);
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(estimatedTime);
		System.out.println("Counter = " + obj.getCounter());
	}
}


//////////////////////////////////////////////////////////////////////

public class Synchronized_Tests{
	public static void main(String[] args) 
	{
		Tester tests = new Tester();
		// tests.Synchronized_Method_Test();
		tests.ReentrantLock_Test();
		
	}
}