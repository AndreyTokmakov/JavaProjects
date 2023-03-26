/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Class tests 
*
* @name    : ProcessBuilderTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 19, 2020
****************************************************************************/ 

package Lang;

import java.lang.reflect.Constructor;

class TestClass {
	public TestClass() {
		System.out.println("public TestClass() called");
	}
	
	public TestClass(int t) {
		System.out.println("public TestClass(int t) called");
	}
	
	public TestClass(String str) {
		System.out.println("public TestClass(String str) called");
	}
}

class Class_Tester {
	public void Get_Constructor() throws NoSuchMethodException, SecurityException 
	{
		Class<TestClass> cls = TestClass.class;
		
        Constructor<TestClass> cons = cls.getConstructor();
        System.out.println(cons);
        
        cons = cls.getConstructor(int.class);
        System.out.println(cons);
        
        cons = cls.getConstructor(String.class);
        System.out.println(cons);
	}
}

public class Class_Test {
	protected static final Class_Tester tester = new Class_Tester();
	
	public static void main(String[] args)
			throws NoSuchMethodException, SecurityException 
	{
		tester.Get_Constructor();
	}
}
