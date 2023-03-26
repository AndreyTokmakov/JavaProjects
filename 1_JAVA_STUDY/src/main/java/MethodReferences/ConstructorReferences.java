/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ConstructorReferences demo class
*
* @name    : ConstructorReferences.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 17, 2020
****************************************************************************/ 

package MethodReferences;

interface MyFunc {
	public MyClass create(String str);
};

class MyClass {
	
	protected String str = "";
	
	public MyClass() {
		System.out.println("MyClass::MyClass()");
	}
	
	public MyClass(String str) {
		this.str = str;
		System.out.println("MyClass::MyClass(String str)");
	}
	
	String getValue() {
		return this.str;
	}
}


public class ConstructorReferences {
	public static void main(String[] args) 
	{
		MyFunc builder = MyClass::new;
		
		MyClass obj = builder.create("SomeInputStringValue");
		System.out.println(obj.getValue());
	}
}
