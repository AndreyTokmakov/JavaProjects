/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Boolean wrapper tests
*
* @name    : Boolean_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 18, 2020
****************************************************************************/ 

package Wrappers;

class Boolean_Tester {
	
	protected void parseBoolean(){
		// parsing different Strings
		boolean b1 = Boolean.parseBoolean("True");
		boolean b2 = Boolean.parseBoolean("TruE");
		boolean b3 = Boolean.parseBoolean("False");
		boolean b4 = Boolean.parseBoolean("FALSE");
		boolean b5 = Boolean.parseBoolean("GeeksForGeeks");
		  
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
	}
	
	protected void valueOf(){
		// creating boolean variable
		boolean b1 = true;
		boolean b2 = false;
		  
		// getting Boolean objects from boolean variables
		Boolean b3 = Boolean.valueOf(b1);
		Boolean b4 = Boolean.valueOf(b2);
		  
		System.out.println(b3);
		System.out.println(b4);
	}
	
	protected void valueOf_1(){
		Boolean b1 = Boolean.valueOf("true");
		Boolean b2 = Boolean.valueOf("TRue");
		Boolean b3 = Boolean.valueOf("False");
		Boolean b4 = Boolean.valueOf("GeeksForGeeks");
		Boolean b5 = Boolean.valueOf(null);
		  
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
	}
	
	protected void hashCodeTest(){
		Boolean b1 = new Boolean("True");
		Boolean b2 = new Boolean("False");
		Boolean b3 = new Boolean("TRue");
		Boolean b4 = new Boolean(null);
		 
		System.out.println(b1.hashCode());
		System.out.println(b2.hashCode());
		System.out.println(b3.hashCode());
		System.out.println(b4.hashCode());
	}
	
	protected void comparetoTest() {
		Boolean b1 = new Boolean("True");
		Boolean b2 = new Boolean("False");
		Boolean b3 = new Boolean("TRue");
		Boolean b4 = new Boolean(null);
		
		//comparing b1,b2,b3,b4
		System.out.println(b1.compareTo(b2));
		System.out.println(b1.compareTo(b3));
		System.out.println(b2.compareTo(b1));
		System.out.println(b1.compareTo(b4));
		System.out.println(b2.compareTo(b4));
		
		// The following statement throws NullPointerExcetion
		//  System.out.println(b1.compareTo(null));
	}
}

public class Boolean_Tests {
	private static Boolean_Tester tests = new Boolean_Tester();
	
	public static void main(String[] args) 
	{
		// tests.parseBoolean();
		// tests.valueOf();
		// tests.valueOf_1();
		// tests.hashCodeTest();
		tests.comparetoTest();
	}
}
