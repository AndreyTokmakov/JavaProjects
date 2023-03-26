/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* AllocationTest.java class
*
* @name    : AllocationTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 3, 2021
****************************************************************************/

package Memory;

class Tester {
	/** **/
	private final static long TEST_REPS_COUNT = 1000000;
	
	/** Buffer size to allocate: **/
	private final static int BUFFER_SIZE = 10 * 1024 * 1024;
	
	protected void RunTest() {
		long start = System.nanoTime();

		for (long n = 0; n < TEST_REPS_COUNT; ++n) {
			byte[] buffer = new byte[BUFFER_SIZE];
		}
		
		long end = System.nanoTime();
		long nsDuration = end - start;
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));  
	}
}

public class AllocationTest {
	public static void main(String[] args) 
	{
		Tester tester = new Tester();
		tester.RunTest();
	}
}
