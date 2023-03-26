/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ExceptionTestContainer_Tests tests
*
* @name    : ExceptionTestContainer_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.test_classes;

import Annotations.test_annotations.ExceptionTest;

public class ExceptionTestContainer_Tests {
	
	@ExceptionTest(ArithmeticException.class)
	@ExceptionTest(NullPointerException.class)
	public static void test1() {
		System.out.println("Method ExceptionTestContainer_Tests::test1() called.");
		int i = 1 / 0;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void test2() {
		System.out.println("Method ExceptionTestContainer_Tests::test2() called.");
		int i = 1 / 1;
	}
}
