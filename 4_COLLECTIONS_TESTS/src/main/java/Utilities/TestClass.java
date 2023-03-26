/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* 
* TestClass class.
* Shall be used for testing
*
* @name   : TestClass.java
* @author : Tokmakov Andrey
* @version: 1.0
* @since  : October 25, 2020
****************************************************************************/ 

package Utilities;

public class TestClass {
	public String name = "";
	public int value = 0;
	
	public TestClass(final String n, int v) {
		this.name = n;
		this.value = v;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("TestClass (id: %s, Name: %s)", this.value, this.name);
	}	
	
    // Overriding the equals of Person class 
	@Override
	public boolean equals(final Object right) {
		if (this == right) 
			return true;
		else if (null == right || right.getClass() != this.getClass())
			return false;
		TestClass obj = (TestClass)right;
		return (this.value == obj.value) &&
			   (this.name.equals(obj.name));
	} 
	
	@Override
	public int hashCode() {
	   return this.name.hashCode() + 31 * this.value;
	}
}