/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* PerformanceTests.java class
*
* @name    : PerformanceTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 30, 2021
****************************************************************************/

package ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class Tester {
	// Concurrent canonicalizing map atop ConcurrentMap - not optimal
	private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
	
	public void Init() {
		
	}
	
	public void Test() {
		/** Start time: **/
		long start = System.currentTimeMillis();
		

		/** End time: **/
		long end = System.currentTimeMillis();
		
		/** Get test run duration: **/
		System.out.println("Test result: " + (end - start));	
	}
}

public class PerformanceTests {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
