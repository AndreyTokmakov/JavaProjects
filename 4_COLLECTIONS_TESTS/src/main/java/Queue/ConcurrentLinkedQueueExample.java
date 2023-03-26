/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name      : ConcurrentLinkedQueueExample.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 03, 2020
****************************************************************************/ 

package Queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample  {
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

    ConcurrentLinkedQueueExample() throws InterruptedException  {
        queue = new ConcurrentLinkedQueue<String>();
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
        consumer.join();
        System.out.println("Done");
    }

    class Producer implements Runnable {
        public void run() {
            System.out.println("Producer started");
            for (int i = 1; i <= 10000; i++) {
            	String str = "String" + i; 
            	queue.add(str);
                //System.out.println("Producer added : " + str);
            }
            cycle = false;
        }
    }
    class Consumer implements Runnable {
        public void run() {
            String str;
            System.out.println("Consumer started\n");
            while (cycle || queue.size() > 0) {
                if ((str = queue.poll()) != null) {
                	//System.out.println("  consumer removed : " + str);
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new ConcurrentLinkedQueueExample();
    }
}