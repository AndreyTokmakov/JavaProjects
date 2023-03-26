/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* StaticNestedClassesTest.java class
*
* @name    : StaticNestedClassesTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 3, 2021
****************************************************************************/

package ObjectOrientedProgramming.NestedClasses;

class TesterForStatic {
	private int value = 10;
	private static int static_value = 10;
	
	private static final class ClassUnderTests {
		
		@SuppressWarnings("unused")
		private static String text = "WE_CAN_HAVE_STATIC_VARIABLE_HERE";
		
		@SuppressWarnings("unused")
		private String text2 = "This_is_OK";
		
		protected void Test()
		{
			// Can not access the non static member of the class 
			// System.out.println("value = " + value);
			
			System.out.println("static_value = " + static_value);
		}
	}
	
	public void Test() 
	{
		ClassUnderTests test = new ClassUnderTests();
		test.Test();
	}
}


class Question { 
    private Type type; 
    
    public Type getType() { 
    	return type; 
    } 
    
    public void setType(Type type) { 
    	this.type = type; 
    } 
 
    public static enum Type { 
        SINGLE_CHOICE, 
        MULIT_CHOICE, 
        TEXT 
    } 
} 


public class StaticNestedClassesTest {
	public static void main(String[] args) 
	{
		/*
		TesterForStatic tester = new TesterForStatic();
		tester.Test();
		*/
		
		Question question = new Question();
		Question.Type type = question.getType(); 
		if (type == Question.Type.TEXT) { 
		
		} 
	}
}
