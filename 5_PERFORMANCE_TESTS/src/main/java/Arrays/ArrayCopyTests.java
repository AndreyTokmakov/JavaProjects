/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ArrayCopyTests.java class
*
* @name    : ArrayCopyTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 16, 2020
****************************************************************************/

package Arrays;

class Tester {
	private final static long TEST_REPS_COUNT = 10000;
	private final static int SIZE = 1000;
	
	private int[] src = new int[SIZE];
	private int[] dst = new int[SIZE];
	
	public Tester() {
		for (int i = 0; i < SIZE; ++i)
			src[i] = i;
	}
	
	private void copy_loop() {
		for (int i = 0; i < SIZE; ++i)
			dst[i] = src[i];
	}
	
	private void copy_sys() {

		System.arraycopy(src, 0, dst, 0, SIZE);
	}

	protected void RunTest() {
		long start = System.nanoTime();

		for (long n = 0; n < TEST_REPS_COUNT; ++n) {
			for (long i = 0; i< TEST_REPS_COUNT; ++i) {
				copy_loop();
				// copy_sys();
			}
		}
		
		long end = System.nanoTime();
		long nsDuration = end - start;
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));  
	}
}

public class ArrayCopyTests {
	public static void main(String[] args) 
	{
		Tester tester = new Tester();
		tester.RunTest();
	}
}
