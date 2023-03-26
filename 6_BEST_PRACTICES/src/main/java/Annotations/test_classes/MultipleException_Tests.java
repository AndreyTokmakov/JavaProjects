/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* MultipleException_Tests tests
*
* @name    : MultipleException_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.test_classes;

import Annotations.test_annotations.MultipleExceptionTest;

public class MultipleException_Tests {
	
	@MultipleExceptionTest({ArithmeticException.class, NullPointerException.class})
	public static void test1() {
		System.out.println("Method MultipleException_Tests::test1() called.");
		int i = 1 / 0;
	}
	

	@MultipleExceptionTest(ArithmeticException.class)
	public static void test2() {
		System.out.println("Method MultipleException_Tests::test2() called.");
		int i = 1 / 1;
	}
}
