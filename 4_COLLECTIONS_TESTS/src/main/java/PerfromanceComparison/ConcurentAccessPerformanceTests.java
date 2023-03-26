package PerfromanceComparison;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;


class ArrayBlockingQueue_TEST
{
	private BlockingQueue<String> queue = null;
	private volatile boolean cycle = true;
	    
	@SuppressWarnings("unused")
	private void Sleep(final long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	void runTest() throws InterruptedException  {
		queue = new ArrayBlockingQueue<String>(100000, true);
		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());
		
		long startTime = System.currentTimeMillis();
		producer.start();
		consumer.start();
		consumer.join();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Test time : " + (endTime - startTime));
	}

	class Producer implements Runnable {
		public void run() {
			System.out.println("Producer started");
			for (int i = 1; i <= 100000000; i++) {
				String str = "String" + i; 
				queue.add(str);
				//System.out.println("Producer added : " + str);
			}
			cycle = false;
		}
	}
	   
	class Consumer implements Runnable {
		public void run() {
			System.out.println("Consumer started\n");
			while (cycle || queue.size() > 0) {
				if (queue.poll() != null) {
					//System.out.println("  consumer removed : " + str);
				}
			}
		}
	}
}

class LinkedBlockingDeque_TEST 
{
	private Queue<String> queue = null;
	private volatile boolean cycle = true;
	    
	@SuppressWarnings("unused")
	private void Sleep(final long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	void runTest() throws InterruptedException  {
		queue = new LinkedBlockingDeque<String>();
		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());
		
		long startTime = System.currentTimeMillis();
		producer.start();
		consumer.start();
		consumer.join();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Test time : " + (endTime - startTime));
	}

	class Producer implements Runnable {
		public void run() {
			System.out.println("Producer started");
			for (int i = 1; i <= 100000000; i++) {
				String str = "String" + i; 
				queue.add(str);
				//System.out.println("Producer added : " + str);
			}
			cycle = false;
		}
	}
	   
	class Consumer implements Runnable {
		public void run() {
			System.out.println("Consumer started\n");
			while (cycle || queue.size() > 0) {
				if (queue.poll() != null) {
					//System.out.println("  consumer removed : " + str);
				}
			}
		}
	}
}



class ConcurrentLinkedQueue_TEST {
	private Queue<String> queue = null;
	private volatile boolean cycle = true;
	    
	@SuppressWarnings("unused")
	private void Sleep(final long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	void runTest() throws InterruptedException  {
		queue = new ConcurrentLinkedQueue<String>();
		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());
		
		long startTime = System.currentTimeMillis();
		producer.start();
		consumer.start();
		consumer.join();
		long endTime = System.currentTimeMillis();
		
		System.out.println("Test time : " + (endTime - startTime));
	}

	class Producer implements Runnable {
		public void run() {
			System.out.println("Producer started");
			for (int i = 1; i <= 100000000; i++) {
				String str = "String" + i; 
				queue.add(str);
				//System.out.println("Producer added : " + str);
			}
			cycle = false;
		}
	}
	   
	class Consumer implements Runnable {
		public void run() {
			System.out.println("Consumer started\n");
			while (cycle || queue.size() > 0) {
				if (queue.poll() != null) {
					//System.out.println("  consumer removed : " + str);
				}
			}
		}
	}
}

////////////////////////////////////////////////////////////////////

public class ConcurentAccessPerformanceTests {
	public static void main(String[] args) throws InterruptedException {
		
		//new ConcurrentLinkedQueue_TEST().runTest();
		//new LinkedBlockingDeque_TEST().runTest();
		new ArrayBlockingQueue_TEST().runTest();
	}
}
