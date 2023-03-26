package Threads.Sync;

import java.util.ArrayList;

class SharedResouce2 {
	protected long counter = 0;
	
	public void Increment() {
		this.counter++;
	}
	
	public long getCounter() {
		return this.counter;
	}
}

class SharedResouce_Sync extends SharedResouce2 {
	@Override
	public synchronized void Increment() {
		this.counter++;
	}
}

class Worker2 implements Runnable {
	/** **/
	protected SharedResouce2 resource;

	public Worker2(SharedResouce2 resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000 * 1000; i++) {
			this.resource.Increment();
		}
	}
}

public class Synchronized_Method_Test {
	
	protected void Test() throws InterruptedException {
		
		//SharedResouce2 resource = new SharedResouce2();
		
		SharedResouce_Sync resource = new SharedResouce_Sync();

		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(new Worker2(resource)));
			threads.get(threads.size() -1).start();
		}
		for (Thread T: threads)
			T.join();
		
		System.out.println("Final resource value is " + resource.getCounter());
	}
	
	public static void main(String[] args) throws InterruptedException {
		Synchronized_Method_Test tests = new Synchronized_Method_Test();
		tests.Test();
	}
}
