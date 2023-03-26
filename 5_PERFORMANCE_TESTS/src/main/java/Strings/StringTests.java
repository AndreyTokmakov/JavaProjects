/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* StringTests.java class
*
* @name    : StringTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 14, 2020
****************************************************************************/

package Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


class Tester {
	private final static long TEST_REPS_COUNT = 1000;

	private StringBuilder strBuilder = new StringBuilder();
	
	protected void Strings_Plus() {
		String str = String.valueOf(ThreadLocalRandom.current().nextInt(10)) +
				     String.valueOf(ThreadLocalRandom.current().nextInt(10)) +
				     String.valueOf(ThreadLocalRandom.current().nextInt(10)) +
				     String.valueOf(ThreadLocalRandom.current().nextInt(10)) +
				     String.valueOf(ThreadLocalRandom.current().nextInt(10)) +
				     String.valueOf(ThreadLocalRandom.current().nextInt(10)) ;
	}
	
	protected void Strings_Format() {
		String str = String.format("%s%s%s%s%s%s","1", "2", "3", "4", "6", "6");
	}
	
	protected void Strings_Concat() {
		String str = new String(String.valueOf(ThreadLocalRandom.current().nextInt(10)))
									.concat(String.valueOf(ThreadLocalRandom.current().nextInt(10)))
				                    .concat(String.valueOf(ThreadLocalRandom.current().nextInt(10)))
				                    .concat(String.valueOf(ThreadLocalRandom.current().nextInt(10)))
				                    .concat(String.valueOf(ThreadLocalRandom.current().nextInt(10)))
				                    .concat(String.valueOf(ThreadLocalRandom.current().nextInt(10)));
	}
	
	protected void Strings_Builder() {
		String str = new StringBuilder(ThreadLocalRandom.current().nextInt(10))
										   .append(ThreadLocalRandom.current().nextInt(10))
										   .append(ThreadLocalRandom.current().nextInt(10))
										   .append(ThreadLocalRandom.current().nextInt(10))
										   .append(ThreadLocalRandom.current().nextInt(10))
										   .append(ThreadLocalRandom.current().nextInt(10)).toString();
	}
	
	protected void Strings_Builder2() {
		strBuilder.setLength(0);
		String str = strBuilder.append((ThreadLocalRandom.current().nextInt(10))).
					append(ThreadLocalRandom.current().nextInt(10))
				   .append(ThreadLocalRandom.current().nextInt(10))
				   .append(ThreadLocalRandom.current().nextInt(10))
				   .append(ThreadLocalRandom.current().nextInt(10))
				   .append(ThreadLocalRandom.current().nextInt(10)).toString();
	}	
	
	protected void RunTest() {
		long start = System.nanoTime();

		for (long n = 0; n < TEST_REPS_COUNT; ++n) {
			for (long i = 0; i< TEST_REPS_COUNT; ++i) {
				// Strings_Plus();
				// Strings_Format();
				// Strings_Concat();
				 
				// Strings_Builder();
				 Strings_Builder2();
			}
		}
		
		long end = System.nanoTime();
		long nsDuration = end - start;
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));  
	}
}

final class SpitStringTests
{
	public List<String> splitByIndex(String text) {
		final List<String> parts = new ArrayList<String>();
		int pos = 0, end;
		while ((end = text.indexOf(' ', pos)) >= 0) {
			parts.add(text.substring(pos, end));
			pos = end + 1;
		}
		parts.add(text.substring(pos, text.length()));
		return parts;
	}

	public List<String> splitTokenizer(String text) {
		final List<String> parts = new ArrayList<String>();
		StringTokenizer defaultTokenizer = new StringTokenizer(text);
		while (defaultTokenizer.hasMoreTokens()) {
			parts.add(defaultTokenizer.nextToken());
		}
		return parts;
	}

	void Tests() {
		for (String s: splitByIndex("1 2 3 4 5")) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s: splitTokenizer("1 2 3 4 5")) {
			System.out.print(s + " ");
		}
	}

	void PerformanceTests()
	{
		final int TESTS_COUNT = 1_000_000;
		String s = "1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9";
		{
			long start = System.nanoTime();
			for (long n = 0; n < TESTS_COUNT; ++n) {

				splitTokenizer(s);
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}
		{
			long start = System.nanoTime();
			for (long n = 0; n < TESTS_COUNT; ++n) {
				splitByIndex(s);
			}
			long nsDuration = System.nanoTime() - start;
			System.out.println(String.format("Slept for %d nano  seconds.", nsDuration));
		}
	}
}

public class StringTests {
	public static void main(String[] args) 
	{
		// Tester tester = new Tester();
		// tester.RunTest();

		SpitStringTests splitTests = new SpitStringTests();
		// splitTests.Tests();
		splitTests.PerformanceTests();
	}
}
