package Threads.Sync;

import java.util.ArrayList;
import java.util.List;
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
	
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockTest tests = new ReentrantLockTest();
		tests.Test();

	}
}
