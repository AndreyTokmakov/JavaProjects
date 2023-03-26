package Sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class SharedResouce {
	public long counter = 0;
}

class Worker_NotSynch implements Runnable {
	/** **/
	protected SharedResouce resource;

	public Worker_NotSynch(SharedResouce resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000 * 1000; i++) {
			this.resource.counter++;
		}
	}
}

class Worker implements Runnable {
	/** **/
	protected SharedResouce resource;
	/** **/
	protected ReentrantLock lock;

	public Worker(SharedResouce resource, 
			      ReentrantLock lock) {
		this.resource = resource;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		lock.lock();
		for (int i = 0; i < 1000 * 1000; i++) {
			this.resource.counter++;
		}
		lock.unlock();
	}
}


public class ReentrantLockTest {
	private void sleep(int msSeconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(msSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void Test() throws InterruptedException {
		SharedResouce resource = new SharedResouce();
		ReentrantLock lock = new ReentrantLock();
		
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Worker(resource, lock)));
			// threads.add(new Thread(new Worker_NotSynch(resource)));
			threads.get(threads.size() -1).start();
		}
		for (Thread T: threads)
			T.join();
		
		System.out.println("Final resource value is " + resource.counter);
	}
	
	protected void Test2() {
		ExecutorService executor  = Executors.newFixedThreadPool(2);
		ReentrantLock lock = new ReentrantLock();
		
		executor.submit(() -> {
			System.out.println(new Date() + ": Task1 stated.");
			lock.lock();
			
			sleep(2000);
			
			System.out.println(new Date() + ": Task1 release lock");
			lock.unlock();
		});
		
		executor.submit(() -> {
			sleep(100);
			System.out.println(new Date() + ": Task2 stated.");
			
			lock.lock();
			lock.unlock();
			System.out.println(new Date() + ": Task2 resumed.");
			
			sleep(2000);
			
			
			System.out.println(new Date() + ": Task2 completed.");
		});
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest tests = new ReentrantLockTest();
		// tests.Test();
		tests.Test2();

	}
}
