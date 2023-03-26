/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Static inner classes tests
*
* @name    : Static_Inner_Class.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 30, 2020
****************************************************************************/ 

class InnerClassTester_NonStatic {
	/** **/
	private String value = "InitialValue";
	
	protected void UseInnerClass() {
		new InnerNonStaticClass().RunTest();
		System.out.println(value);
	}
	
	class InnerNonStaticClass {
		public void RunTest() 
		{
			System.out.println(value);
			value = "New_Value";
		}
	}
}

class InnerClassTester_Static {
	/** **/
	private String value = "InitialValue";
	
	protected void UseInnerClass() {
		new InnerStaticClass().RunTest();
		System.out.println(value);
	}
	
	static class InnerStaticClass {
		public void RunTest() 
		{
			
			// We can not access InnerClassTester_Static.value from the 
			// InnerStaticClass class:
			
			/*
			System.out.println(value);
			value = "New_Value";
			*/
		}
	}
}

public class Static_Inner_Class {
	public static void main(String[] args) 
	{
		InnerClassTester_NonStatic obj = new InnerClassTester_NonStatic();
		obj.UseInnerClass();
	}
}
