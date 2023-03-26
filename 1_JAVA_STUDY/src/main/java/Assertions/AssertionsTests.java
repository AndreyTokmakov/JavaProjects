//============================================================================
// Name        : AssertionsTests.java
// Created on  : September 12, 2020
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Assertions tests
//============================================================================

package Assertions;

class Tester { 
	
	public void Test() 
	{ 
		int a = 15; 
		assert a >= 10 : "Assert failed"; 
		assert a >= 100 : "Assert failed";
		System.out.println("value of a is "+a); 
	} 
	
	public void BoolTest() {
		boolean assertsEnabled = false;
		assert assertsEnabled : "Opppps";
	}
} 

public class AssertionsTests {
	/** **/
	private final static Tester tester = new Tester();
	
	public static void main(String[] args) {
		// tester.Test();
		tester.BoolTest();
	}
}
