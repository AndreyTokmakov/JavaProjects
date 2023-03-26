/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* NestedClassTest.java class
*
* @name    : NestedClassTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 3, 2021
****************************************************************************/

package ObjectOrientedProgramming.NestedClasses;

class TesterForNonStatic {
	private int value = 10;
	private static int static_value = 10;
	
	class ClassUnderTests {
		
		// ERROR !!! Its not allowed here!
		// private static String text = "WE_CAN_HAVE_STATIC_VARIABLE_HERE";
		
		@SuppressWarnings("unused")
		private String text2 = "This_is_OK";
		
		protected void Test()
		{
			// We can access both fields from here!
			System.out.println("value = " + value);
			System.out.println("static_value = " + static_value);
		}
	}
	
	public void Test() 
	{
		ClassUnderTests test = new ClassUnderTests();
		test.Test();
	}
}


public class NestedClassTest {
	public static void main(String[] args)
	{
		TesterForNonStatic tester = new TesterForNonStatic();

		// 	tester.Test();

		TesterForNonStatic.ClassUnderTests T = tester.new ClassUnderTests();
		T.Test();


	}
}
