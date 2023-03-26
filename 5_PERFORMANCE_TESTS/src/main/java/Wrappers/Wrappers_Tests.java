/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Wrappers_Tests.java class
*
* @name    : Wrappers_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 5, 2020
****************************************************************************/

package Wrappers;
 
class Tester {
	private final static long TEST_REPS_COUNT = 1000000000;
	
	protected void Creat_Integer_New() {
		for (int i = 0; i < TEST_REPS_COUNT; ++i)
		{
			 for (int n = 0; n < 64; ++n) {
				 Integer var = new Integer(n);
			 }
		}
	}
	
	protected void Creat_Integer() {
		for (int i = 0; i < TEST_REPS_COUNT; ++i)
		{
			 for (int n = 0; n < 64; ++n) {
				 Integer var = n;
			 }
		}
	}
	
	protected void RunTest() {
		long start = System.nanoTime();

		// Creat_Integer_New();
		 Creat_Integer();
		
		long end = System.nanoTime();
		long nsDuration = end - start;
		System.out.println(String.format("Result: %d nano  seconds.", nsDuration));  
	}
}

public class Wrappers_Tests {
	public static void main(String[] args)
	{
		Tester tester = new Tester();
		tester.RunTest();
	}
}
