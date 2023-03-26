/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ExceptionTests tests
*
* @name    : ExceptionTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.TestFrameworkDemo.test_classes;

import Annotations.TestFrameworkDemo.test_annotations.ExceptionTest;

public class ExceptionTests {
	
	@ExceptionTest(ArithmeticException.class)
	public static void test1() {
		int i = 1 / 0;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void test2() {
		int i = 1 / 1;
	}
}
