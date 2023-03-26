/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java anonymous classes tests
*
* @name    : AnonymousClassesTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 07, 2020
****************************************************************************/ 

package ObjectOrientedProgramming.AnonymousClasses;

// Java program to demonstrate Anonymous inner class 
interface Age  { 
	int x = 21; 
	void getAge(); 
} 

class AnonymousDemo  { 
	public void Test() {
		Age oj1 = new Age() { 
			@Override
			public void getAge() {  // printing age 
				System.out.print("Age is "+x); 
			} 
		}; 
		oj1.getAge(); 
	} 
} 

// Java program to illustrate defining a thread 
// Using Anonymous Inner class that implements an interface 
class MyThread { 
	public void Test() {
		// Here we are using Anonymous Inner class 
		// that implements a interface i.e. Here Runnable interface 
		Runnable r = new Runnable() { 
			public void run() { 
				System.out.println("Child Thread"); 
			} 
		}; 
		Thread t = new Thread(r); 
		t.start(); 
		System.out.println("Main Thread"); 
	} 
} 

public class AnonymousClassesTest {
	public static void main(String[] args) {
		// AnonymousDemo demo = new AnonymousDemo();
		// demo.Test();
		
		MyThread T = new MyThread();
		T.Test();
	}
}
