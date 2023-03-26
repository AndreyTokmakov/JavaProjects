/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ArrayDeque tests
*
* @name    : ArrayDeque_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 25, 2020
****************************************************************************/ 

package Collections.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

import Collections.Utilities.Person;

public class ArrayDeque_Tests {

	public static void Test() {
		Deque<String> states = new ArrayDeque<String>();
		  
		states.add("Germany");
		states.addFirst("France");
		states.push("Great Britain");
		states.addLast("Spain");
		states.add("Italy");
	          
		String sFirst = states.getFirst();
		System.out.println(sFirst); 
		
		String sLast = states.getLast();
		
		System.out.println(sLast);
		System.out.printf("Queue size: %d \n", states.size());  // 5

		while (null != states.peek())
			System.out.println(states.pop());
	}
	
	public static void Test1() {
		ArrayDeque<Person> people = new ArrayDeque<Person>();
		people.addFirst(new Person(0, "Tom"));
		people.addLast(new Person(1, "Nick"));

		for (Person p : people){
			System.out.println(p.getName());
		}
	}
	
	public static void main(String[] args) {
		 Test();
		// Test1();
	}
}
