/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ArrayDeque tests
*
* @name    : ArrayDeque_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 25, 2020
****************************************************************************/ 

package Collections.PerfromanceComparison;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class ArrayDeque_vs_LinkedList {
	public static void main(String[] args) throws InterruptedException {
		ArrayDeque_vs_LinkedList_PerfromanceTester tester = 
				new ArrayDeque_vs_LinkedList_PerfromanceTester();
		tester.RunTests();
	}
}

class ArrayDeque_vs_LinkedList_PerfromanceTester {
	// Test driver:
	protected void RunTests() throws InterruptedException {
		this.Compare_AddElements();
	}
	
	/**************************** Specific tests below: ****************************/
	
	protected void Compare_AddElements() throws InterruptedException {	
		{
			long start = System.currentTimeMillis(); // Start time:
			
			for (int n = 0; n < 10; n++) {
				LinkedList<String> collection = new LinkedList<String>();
				String someString = "Some_long_string_for_testing";
				for (int i = 0; i < 10000000; i++) {
					collection.add(someString);
				}
				collection.clear();
			}
			
			long end = System.currentTimeMillis(); // End time:
			System.out.println("LinkedList result : " + (end - start)); // Get test run duration:
		}
		{
			long start = System.currentTimeMillis(); // Start time:
			
			for (int n = 0; n < 10; n++) {
				ArrayDeque<String> collection = new ArrayDeque<String>();
				String someString = "Some_long_string_for_testing";
				for (int i = 0; i < 10000000; i++) {
					collection.add(someString);
				}
				collection.clear();
			}
			
			long end = System.currentTimeMillis(); // End time:
			System.out.println("LinkedList result : " + (end - start)); // Get test run duration:
		}

	}
}
