/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ReturnValuesTests.java class
*
* @name    : ReturnValuesTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 5, 2020
****************************************************************************/

package Return_Types;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Tester {
	private final static long TEST_REPS_COUNT = 1000000000;
	
	protected List<String> ReturnList_Null() {
		return null;
	}
	
	protected List<String> ReturnList_EmptyList() {
		return Collections.<String>emptyList();
	}
	
	protected void Return_Null() {
		for (long i = 0; i < Tester.TEST_REPS_COUNT; ++i) {
			@SuppressWarnings("unused")
			List<String> list = ReturnList_Null();
		}
	}
	
	protected void Return_EmptyList() {
		for (long i = 0; i < Tester.TEST_REPS_COUNT; ++i) {
			@SuppressWarnings("unused")
			List<String> list = ReturnList_EmptyList();
		}
	}
	
	protected void RunTest() {
		long start = System.nanoTime();
		
		 Return_Null();
		// Return_EmptyList();
		
		long end = System.nanoTime();
		
		long nsDuration = end - start;
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));  
	}
}

public class ReturnValuesTests {
	public static void main(String[] args)
	{
		Tester tester = new Tester();
		tester.RunTest();
	}
}
