/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name    : ObeserverDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
* 
****************************************************************************/ 

package ObjectOrientedProgramming.Copy;

/** TestObject class. **/
class TestObject { 
    protected String name = "";
      
    // A normal parametrized constructor  
    public TestObject(final String name) { 
    	System.out.println("Normal constructor called"); 
        this.name = name; 
    } 
      
    // Copy constructor 
    public TestObject(final TestObject obj) { 
        System.out.println("Copy constructor called"); 
        this.name = obj.name; 
    } 
       
    // Overriding the toString of Object class 
    @Override
    public String toString() { 
        return String.format("TestObject(%s)", this.name);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
    
    
} 

public class CopyConstructorTest {
	
	public static void Test1() {
		TestObject obj1 = new TestObject("TestValue"); 
		System.out.println("obj1: " + obj1); 
        
        // Following involves a copy constructor call 
		TestObject obj2 = new TestObject(obj1); 
		System.out.println("obj2: " + obj2);
  
        // Note that following doesn't involve a copy constructor call as  
        // non-primitive variables are just references. 
		TestObject obj3 = obj2;    
		System.out.println("obj3: " + obj3);
	}
	
	public static void Test2() {
		TestObject obj1 = new TestObject("TestValue"); 
		System.out.println("obj1: " + obj1);
		
		TestObject obj2 = new TestObject(obj1);
		System.out.println("obj2: " + obj2);
		
		TestObject obj3 = obj2;    
		obj3.setName("TestValue_NEW");
		System.out.println("obj1: " + obj1);
		System.out.println("obj2: " + obj2);
		
		

	}
	
	public static void main(String[] args) {
		// Test1();
		Test2();
		
	}
}
