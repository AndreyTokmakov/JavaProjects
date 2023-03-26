/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Reflection methods tests
*
* @name    : MethodsTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 21, 2020
****************************************************************************/ 

package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodsTests {
	/** TestObject class instance: **/
	private static TestObject obj = new TestObject("12345"); 
	
	protected static void List_Class_Methods() {
        // Creating class object from the object using getclass method 
        Class<? extends TestObject> cls = obj.getClass(); 
        System.out.println("The name of class is " + cls.getName()); 

        System.out.println("\n" + cls.getName() + " class methods:"); 
             
        // Getting methods of the class through the object of the class by using getMethods 
        final Method[] methods = cls.getMethods(); 
        for (Method method:methods) 
            System.out.println(method.getName()); 
	}
	
	protected static void Call_Method_ByName() 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<? extends TestObject> cls = obj.getClass(); 
		Method methodcall1 = cls.getDeclaredMethod("method2", int.class); 
		
		// invokes the method at runtime 
        methodcall1.invoke(obj, 19); 
	}
	
	
	protected static void Call_Method_ByName_Private() 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<? extends TestObject> cls = obj.getClass(); 
		Method method = cls.getDeclaredMethod("method3"); 
		
		method.setAccessible(true);
		method.invoke(obj); 
	}
	
	
	protected static void Access_Field() 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<? extends TestObject> cls = obj.getClass(); 
        Field field = cls.getDeclaredField("value"); 
        
        System.out.println(obj.getValue());
  
        // allows the object to access the field irrespective of the access specifier used with the field 
        field.setAccessible(true); 
        
        // takes object and the new value to be assigned to the field as arguments 
        field.set(obj, "JAVA"); 
        
        System.out.println(obj.getValue());
	} 
	
	protected static void Access_Field_Private() 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<? extends TestObject> cls = obj.getClass(); 
        Field field = cls.getDeclaredField("value"); 
        
        System.out.println(obj.getValue());
        field.set(obj, "JAVA"); 
        System.out.println(obj.getValue());
	} 
	
	protected static void Access_Field_WrongName() 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<? extends TestObject> cls = obj.getClass(); 
        Field field = cls.getDeclaredField("value44"); 
        
        System.out.println(obj.getValue());

        field.setAccessible(true); 
        field.set(obj, "JAVA"); 
        
        System.out.println(obj.getValue());
	} 
	
	
	public static void main(String[] args) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException,
			       IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// List_Class_Methods();
		
		// Call_Method_ByName();
		// Call_Method_ByName_Private();
		
		// Access_Field();
		// Access_Field_Private();
		// Access_Field_WrongName();
	}
}
