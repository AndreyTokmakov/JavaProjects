/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name    : ObeserverDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 08, 2020
****************************************************************************/ 

package Sync;

import java.util.ArrayList;

class SynchronizedBlocksTester {
	
	static interface IResouce {
		public void Increment();
		public long getCounter();
	}
	
	static class Resouce implements IResouce {
		protected long counter = 0;
		
		@Override
		public void Increment() {
			this.counter++;
		}
		
		@Override
		public long getCounter() {
			return this.counter;
		}
	}
	
	static class ResouceSynchronized implements IResouce {
		protected long counter = 0;
		
		private Object lock = new Object();
		
		@Override
		public void Increment() {
			// this is the syntax for a synchronized block
			
			// synchronized (lock) { OR like this
			
			synchronized (this) {
				this.counter++;
			}
		}
		
		@Override
		public long getCounter() {
			return this.counter;
		}
	}

	static class Worker implements Runnable {
		/** **/
		protected IResouce resource;

		public Worker(IResouce resource) {
			this.resource = resource;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < 1000 * 10000; i++) {
				this.resource.Increment();
			}
		}
	}

	//-----------------------------------------------------------------------------//
	
	protected void _run_test(IResouce resImpl, String text) throws InterruptedException {
		IResouce resource = resImpl;	
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Worker(resource)));
			threads.get(threads.size() -1).start();
		}
		for (Thread T: threads)
			T.join();
		System.out.println(text + resource.getCounter());
	}
	
	protected void Sync_Test() throws InterruptedException {
		_run_test(new ResouceSynchronized(), "Synchronized tests: ");
	}
	
	protected void Not_Sync_Test() throws InterruptedException {
		_run_test(new Resouce(), "Not Synchronized tests: ");
	}
};

public class SynchronizedBlocksTests {
	public static void main(String[] args) throws InterruptedException {
		SynchronizedBlocksTester tester = new SynchronizedBlocksTester();
		tester.Not_Sync_Test();
		tester.Sync_Test();
	}
}
