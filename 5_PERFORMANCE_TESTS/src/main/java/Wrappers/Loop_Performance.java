/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Loop_Performance.java class
*
* @name    : Loop_Performance.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 6, 2020
****************************************************************************/

package Wrappers;

import java.util.ArrayList;
import java.util.List;

class MyInteger {
	private int value = 0;
	
	public MyInteger(int v) {
		this.value = v;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

class Loop_Tester {
	private final static long TEST_REPS_COUNT = 1000000;
	private final static int ARRAY_SIZE = 10000;

	private final static int[] intArray = new int[Loop_Tester.ARRAY_SIZE];
	private final static List<MyInteger> list = new ArrayList<MyInteger>();
	
	static {
		for (int i = 0; i < Loop_Tester.ARRAY_SIZE; ++i) {
			intArray[i] = i;
			list.add(new MyInteger(i));
		}
	}
	
	protected void Loop_By_Index() {
		long sum = 0;
		for (int i = 0; i < Loop_Tester.TEST_REPS_COUNT; ++i)
		{
			 for (int n = 0; n < Loop_Tester.ARRAY_SIZE; ++n) {
				 sum += intArray[n];
			 }
		}
	}
	
	protected void Loop_For_Each() {
		long sum = 0;
		for (int i = 0; i < Loop_Tester.TEST_REPS_COUNT; ++i)
		{
			 for (MyInteger n: list) {
				 sum += n.getValue();
			 }
		}
	}
	
	protected void RunTest() {
		long start = System.nanoTime();

		// Loop_By_Index();
		 Loop_For_Each();
		
		long end = System.nanoTime();
		long nsDuration = end - start;
		System.out.println(String.format("Result: %d nano  seconds.", nsDuration));  
	}
}



public class Loop_Performance {
	public static void main(String[] args)
	{
		Loop_Tester tester = new Loop_Tester();
		tester.RunTest();
	}
}
