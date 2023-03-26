/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Sample tests
*
* @name    : Sample.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.TestFrameworkDemo.test_classes;

import Annotations.TestFrameworkDemo.test_annotations.MyTest;

public class MyTest_Tests {
	@MyTest
	public static void m1() { }

	public static void m2() { }

	@MyTest
	public static void m3() {
		throw new RuntimeException("boom");
	}

	@MyTest
	public void m4() {
		/** Invalid usage, not static. **/
	}
}