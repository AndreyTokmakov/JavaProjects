/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* BarrierDemo class
*
* @name    : BarrierDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
****************************************************************************/ 

package CyclicBarrier_Tests;

import java.util.concurrent.*;

class MyThread implements Runnable {
    private CyclicBarrier barrier;
    private String name;

    public MyThread(CyclicBarrier c, String n) {
		this.barrier = c;
		this.name = n;
		new Thread(this).start();
    }

    public void run() {
		try {
			System.out.println(name + " waiting .....");
			barrier.await();
			System.out.println(name + " done!");
		} catch (BrokenBarrierException exc) {
		    System.out.println(exc);
		} catch (InterruptedException exc) {
		    System.out.println(exc);
		}
    }
}

class BarrirAction implements Runnable {
    public void run() {
    	System.out.println("Barrier reached");
    }
}

public class BarrierDemo {
	
	protected static void Test1() {
        CyclicBarrier cb = new CyclicBarrier(3, new BarrirAction());
		System.out.println("Run threads");
	
		new MyThread(cb, "A");
		new MyThread(cb, "B");
		new MyThread(cb, "C");	
	}
	
	protected static void Test2() {
        CyclicBarrier cb = new CyclicBarrier(3, new BarrirAction());
		System.out.println("Run threads");
	
		new MyThread(cb, "A");
		new MyThread(cb, "B");
		new MyThread(cb, "C");	
		new MyThread(cb, "Z");
		new MyThread(cb, "Y");
		new MyThread(cb, "Z");	
	}
	
    public static void main(String args[])
    {
    	// Test1();
    	Test2();
    }
}
