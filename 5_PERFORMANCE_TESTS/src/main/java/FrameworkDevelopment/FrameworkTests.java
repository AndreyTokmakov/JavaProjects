/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Performance tests FrameworkDevelopment class
*
* @name    : FrameworkDevelopment.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 29, 2020
****************************************************************************/ 

package FrameworkDevelopment;

import java.util.concurrent.TimeUnit;

public class FrameworkTests {
	
	protected static void SimpleTest() throws InterruptedException {
		long start = System.nanoTime();
		
		TimeUnit.SECONDS.sleep(1);
		
		long end = System.nanoTime();
		
		long nsDuration = end - start;
		long mksDuration = TimeUnit.NANOSECONDS.toMicros(nsDuration);
		long msDuration = TimeUnit.NANOSECONDS.toMillis(nsDuration);
		
		System.out.println(String.format("Slept for %d nano  seconds.", nsDuration)); 
		System.out.println(String.format("Slept for %d micro seconds.", mksDuration)); 
		System.out.println(String.format("Slept for %d milli seconds.", msDuration)); 
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		SimpleTest();
	}
}
