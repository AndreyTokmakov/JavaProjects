/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* 
* Person class.
* Shall be used for testing
*
* @name   : Person.java
* @author : Tokmakov Andrey
* @version: 1.0
* @since  : October 25, 2020
****************************************************************************/ 

package Utilities;

public class Person {
	private int pid = -1;
	private String name = "Unnamed";
	
	public Person() {
		// TODO:
	}
	
	public Person(int pid, String name){
		this.pid = pid;
		this.name = name;
	}
	
	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("Person (id: %s, Name: %s)", this.pid, this.name);
	}	
	
    // Overriding the equals of Person class 
	@Override
	public boolean equals(final Object right) {
		if (this == right) 
			return true;
		else if (null == right || right.getClass() != this.getClass())
			return false;
		Person person = (Person)right;
		return (this.pid == person.pid) &&
			   (this.name.equals(person.name));
	} 
	
	@Override
	public int hashCode() {
	   return this.name.hashCode() + 31 * this.pid;
	}
}
