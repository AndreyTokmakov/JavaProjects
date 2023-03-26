/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java.lang.Math tests 
*
* @name    : Math.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 19, 2020
****************************************************************************/ 

package Math;

public class Math_Tests {
	public static void main(String[] args) 
	{
		System.out.println("Abs(-10) = " + Math.abs(-10));

		System.out.println("\nCeil(1.5) = " + Math.ceil(1.5));
		System.out.println("Ceil(1.1) = " + Math.ceil(1.1));
		System.out.println("pow(3, 5) = " + (int)Math.pow((int)3, (int)5));
	}
}
