/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* TestObject class definition
*
* @name    : TestObject.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 21, 2020
****************************************************************************/ 

package Reflection;

public class TestObject {
	
	private String value;
	
	@SuppressWarnings("unused")
	private String value1;
	
	@SuppressWarnings("unused")
	private String value2;
	
	@SuppressWarnings("unused")
	public String public_Field_1;
	
	@SuppressWarnings("unused")
	public String public_Field_2;
	
	public TestObject() {
		this("");
	}
	
	public TestObject(final String str) {
		this.value = str;
	}
	
    // Copy constructor 
    public TestObject(final TestObject obj) { 
        this.value = obj.value; 
    } 
       
	public String getValue() {
		return this.value;
	}

	public void setValue(final String name) {
		this.value = name;
	} 
	
    // Overriding the toString of BaseObject class 
    @Override
    public String toString() { 
        return String.format("BaseObject(%s)", this.value);
    }

    // Overriding the equals of BaseObject class 
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		else if (null == obj || obj.getClass() != this.getClass())
			return false;
		return this.value.equals(((TestObject)obj).value);
	} 
	
	@Override
	public int hashCode() {
	   int result = value.hashCode();  
	   return result;
	}

    public void method1()  { 
        System.out.println("The string is " + value); 
    } 
  
    public void method2(int n)  { 
        System.out.println("The number is " + n); 
    } 
  
    @SuppressWarnings("unused")
	private void method3() { 
        System.out.println("Private method invoked"); 
    } 
}
