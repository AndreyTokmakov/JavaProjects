/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Reference Queue tests
*
* @name    : ReferenceQueue_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 30, 2020
****************************************************************************/ 

package Memory;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

class TestClass {
	private StringBuffer data;
	
	public TestClass() {
		this.data = new StringBuffer();
		for (long i = 0; i < 50000000; i++) {
			this.data.append('x');
		}
	}
	   
	@Override
	protected void finalize() {
		System.out.println("TestClass::finalize()");
	}
}

class MyPhantomReference extends PhantomReference<TestClass> {

	public MyPhantomReference(TestClass obj, ReferenceQueue<TestClass> queue) {
		super(obj, queue);
		Thread thread = new QueueReadingThread<TestClass>(queue);
		thread.start();
	}

	public void cleanup() {
		System.out.println("Clearing the phantom link! Deleting an object from memory!");
		clear();
	}
}

class QueueReadingThread<TestClass> extends Thread {

	private ReferenceQueue<TestClass> referenceQueue;

	public QueueReadingThread(ReferenceQueue<TestClass> referenceQueue) {
		this.referenceQueue = referenceQueue;
	}

	@Override
	public void run() {
		System.out.println("The thread tracking the queue has started!");
		Reference ref = null;

		while ((ref = referenceQueue.poll()) == null) {
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				throw new RuntimeException("Thread " + getName() + " interrupted!!");
			}
		}
		((MyPhantomReference) ref).cleanup();
	}
}

class ReferenceQueueTester {
	
	protected void Test() throws InterruptedException 
	{
		Thread.sleep(10000);

		ReferenceQueue<TestClass> queue = new ReferenceQueue<>();
		Reference ref = new MyPhantomReference(new TestClass(), queue);

		System.out.println("ref = " + ref);
		Thread.sleep(5000);
		
		System.out.println("GC shall be called");
		System.gc();
		Thread.sleep(300);
		
		System.out.println("ref = " + ref);
		Thread.sleep(5000);
		System.out.println("GC shall be called");

		System.gc();
	}
}

public class ReferenceQueue_Tests {
	public static void main(String[] args) throws InterruptedException 
	{
		ReferenceQueueTester tester = new ReferenceQueueTester();
		tester.Test();

	}
}
