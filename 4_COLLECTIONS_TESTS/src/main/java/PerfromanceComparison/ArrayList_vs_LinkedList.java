/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ArrayList vs LinkedList performance tests.
*
* @name    : ArrayList_vs_LinkedList.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 25, 2020
****************************************************************************/ 

package PerfromanceComparison;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayList_vs_LinkedList {
	public static void main(String[] args) throws InterruptedException {
		ArrayList_vs_LinkedList_PerfromanceTester tester = 
				new ArrayList_vs_LinkedList_PerfromanceTester();
		tester.RunTests();
	}
}

class ArrayList_vs_LinkedList_PerfromanceTester {
	// Test driver:
	protected void RunTests() throws InterruptedException {
		this.Compare_AddElements();
	}
	
	/**************************** Specific tests below: ****************************/
	
	protected void Compare_AddElements() throws InterruptedException {	
		final int count = 50000000;

		{
			/** Start time: **/
			long start = System.currentTimeMillis();
			
			LinkedList<String> string_linked_list = new LinkedList<String>();
			String someString = "Some_long_string_for_testing";
			for (int i = 0; i < count; i++) {
				string_linked_list.add(someString);
			}
			string_linked_list.clear();
			
			/** End time: **/
			long end = System.currentTimeMillis();
			
			/** Get test run duration: **/
			System.out.println("LinkedList result : " + (end - start));
		}

		{
			/** Start time: **/
			long start = System.currentTimeMillis();
			
			ArrayList<String> array_linked_list = new ArrayList<String>();
			String someString = "Some_long_string_for_testing";
			for (int i = 0; i < count; i++) {
				array_linked_list.add(someString);
			}
			array_linked_list.clear();
			
			/** End time: **/
			long end = System.currentTimeMillis();
			
			/** Get test run duration: **/
			System.out.println("ArrayList result : " + (end - start));
		}

	}
}
