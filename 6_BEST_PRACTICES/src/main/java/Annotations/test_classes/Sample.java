/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Sample tests
*
* @name    : Sample.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 02, 2020
****************************************************************************/ 

package Annotations.test_classes;

import Annotations.test_annotations.MyTest;

public class Sample {
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