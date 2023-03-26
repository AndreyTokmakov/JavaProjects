/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Streans_Vs_Loops.java class
*
* @name    : Streans_Vs_Loops.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 4, 2020
****************************************************************************/

package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

class Tester {
	private final static int TEST_REPS_COUNT = 100000;
	private final static int SIZE = 10000;
	
	private List<Integer> list = new ArrayList<Integer>(SIZE);
	
	{
		for (int i = 0; i < SIZE; ++i) {
			list.add(i);
		}
	}

	protected void Test_Loop() {
		List<Integer> result = new ArrayList<Integer>(SIZE);
		for (int i = 0; i < TEST_REPS_COUNT; i++)
		{
			for (Integer val: list) {
				if (0 == val % 2)
					result.add(val);
			}
			result.clear();
		}
	}
	
	protected void Test_Loop_Count() {
		long count = 0;
		for (int i = 0; i < TEST_REPS_COUNT; i++) {
			for (Integer val: list) {
				if (0 == val % 2)
					count++;
			}
			//count = 0;
		}
		System.out.println(count);
	}
	
	protected void Test_Loop_Count2() {
		long count = 0;
		for (int i = 0; i < TEST_REPS_COUNT; i++) {
			for (int n = 0; n < list.size(); n++) {
				if (0 == list.get(n) % 2)
					count++;
			}
			//count = 0;
		}
		System.out.println(count);
	}
	
	protected void Test_Stream_Count() {
		long count = 0;
		for (int i = 0; i < TEST_REPS_COUNT; i++)
		{
			count += list.stream().filter(v -> 0 == v % 2).count();
			//count = 0;
		}
		System.out.println(count);
	}
	
	protected void Test_Stream() {
		for (int i = 0; i < TEST_REPS_COUNT; i++)
		{
			Stream<Integer> stream = list.stream().filter(v -> 0 == v % 2);
		}
	}
	
	protected void RunTest() {
		long start = System.nanoTime();
		
		// Test_Loop();
		// Test_Stream();

		// Test_Loop_Count();
		 Test_Loop_Count2();
		// Test_Stream_Count();
		
		long end = System.nanoTime();
		
		long nsDuration = end - start;
		long mksDuration = TimeUnit.NANOSECONDS.toMicros(nsDuration);
		long msDuration = TimeUnit.NANOSECONDS.toMillis(nsDuration);
		
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration)); 
		System.out.println(String.format("Slept for %d micro seconds.", mksDuration)); 
		System.out.println(String.format("Slept for %d milli seconds.", msDuration)); 
	}
}

public class Streans_Vs_Loops {
	public static void main(String[] args)
	{
		Tester tester = new Tester();
		tester.RunTest();
	}
}
