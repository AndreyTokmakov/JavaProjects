/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Reflection methods tests
*
* @name    : Field_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 22, 2020
****************************************************************************/ 

package Reflection;

import java.lang.reflect.Field;

public class Fields_Tests {
	
	/** TestObject class instance: **/
	private static TestObject obj = new TestObject("12345"); 
	
	private static void List_Fields() {
		Class<? extends TestObject> carClass = obj.getClass();
		Field[] declaredFields = carClass.getDeclaredFields();
		for (Field field :declaredFields) {
		    System.out.println(field);
		}
	}
	
	private static void List_Public_Fields() {
		Class<? extends TestObject> carClass = obj.getClass();
		Field[] declaredFields = carClass.getFields();
		for (Field field :declaredFields) {
		    System.out.println(field);
		}
	}

	public static void main(String[] args) {
		 List_Fields();
		// List_Public_Fields();
	}
}
