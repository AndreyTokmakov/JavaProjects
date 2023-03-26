/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Reflection_Tests.java class
*
* @name    : Reflection_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Arrays;

import java.lang.reflect.Array;

public class Reflection_Tests {

	public static void test1() {
		String[] stringArray = (String[]) Array.newInstance(String.class, 3);

		Array.set(stringArray, 0, "Mahesh");
		Array.set(stringArray, 1, "Ramesh");
		Array.set(stringArray, 2, "Suresh");

		System.out.println("stringArray[0] = " + Array.get(stringArray, 0));
		System.out.println("stringArray[1] = " + Array.get(stringArray, 1));
		System.out.println("stringArray[2] = " + Array.get(stringArray, 2));
	}
	
	public static void main(String[] args) 
	{
		
		test1();
	}
}
