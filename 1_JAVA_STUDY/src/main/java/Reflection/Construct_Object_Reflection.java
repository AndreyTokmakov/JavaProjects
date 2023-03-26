/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* Construct_Object_Reflection.java class
*
* @name    : Construct_Object_Reflection.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 11, 2021
****************************************************************************/

package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

class TestBase {
	private String name = "";
	private String description = "";
	
	public TestBase() {
		this("", "");
	}
	
	public TestBase(String name) {
		this(name, "");
	}
	
	public TestBase(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TestBase(" + name + ", " + description + ")";
	}	
}

class ConstructorTester {
	
	public void getContructors() {
		final Class<?> clsObject = TestBase.class;
		List<Constructor<?>> constructors = Arrays.asList(clsObject.getConstructors());
		System.out.println(constructors);
	}
	
	public void getSpesificConstructor() throws NoSuchMethodException, SecurityException {
		final Class<?> clsObject = TestBase.class;
		
		
		Constructor<?> constructor = clsObject.getConstructor(new Class[]{String.class});
		System.out.println("Constructor: " + constructor);
		
		List<Class<?>> parameterTypes = Arrays.asList(constructor.getParameterTypes());
		System.out.println("With params: " + parameterTypes);
	}
	
	public void createObject() throws NoSuchMethodException, SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Class<?> clsObject = TestBase.class;
		Constructor<?> constructor = clsObject.getConstructor(new Class[]{String.class, String.class});
		
		TestBase obj = (TestBase) constructor.newInstance("NameTest", "DescriptionTest");
		System.out.println(obj);
	}
}


public class Construct_Object_Reflection 
{
	public static void main(String ... params) 
			throws NoSuchMethodException, SecurityException, InstantiationException,
				   IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ConstructorTester tests = new ConstructorTester();
		
		// tests.getContructors();
		// tests.getSpesificConstructor();
		tests.createObject();
	}
}
