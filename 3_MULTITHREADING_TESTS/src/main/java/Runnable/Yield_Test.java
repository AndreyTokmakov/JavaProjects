/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Yield_Test.java class
*
* @name    : Yield_Test.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 9, 2021
****************************************************************************/

package Runnable;

class Producer extends Thread {
   public void run()  {
      for (int i = 0; i < 5; i++) {
         System.out.println("Producer: Produced Item " + i);
         Thread.yield();
      }
   }
}

class Consumer extends Thread {
   public void run() {
      for (int i = 0; i < 5; i++) {
         System.out.println("Consumer: Consumed Item " + i);
         Thread.yield();
      }
   }
}

public class Yield_Test {
	
	public static void main(String[] args)
	{
		Thread producer = new Producer();
	    Thread consumer = new Consumer();
	       
	    producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
	    consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority
	       
	    producer.start();
	    consumer.start();
	}
}
