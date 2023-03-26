/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Interfaces tests
*
* @name    : Interfaces_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
* 
****************************************************************************/ 

package ObjectOrientedProgramming.Interfaces;

public class Interfaces_Tests {
	
	private static interface Testable {
		/** Some field: **/
		public static String value = "";
		
		/** Some method: **/
		public void method1();
		
		/** Static method: **/
		public static void method2() {
			System.out.println("static ITest1::method2()");
		}
		
		/** Default method: **/
		public default void method3() {
			System.out.println("default ITest1::method3()");
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
