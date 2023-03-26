//============================================================================
// Name        : LinkedList_Tests.java
// Created on  : October 01, 2020
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : File list utilities test class
//============================================================================

package List;

import java.util.Arrays;
import java.util.LinkedList;

class LinkedListTestser {
	
	public void Poll() {
		LinkedList<String> strings = new LinkedList<String>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
		System.out.println(strings);

		System.out.println(strings.poll());
		System.out.println(strings);
	}
	
	public void Pollast() {
		LinkedList<String> strings = new LinkedList<String>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
		System.out.println(strings);

		System.out.println(strings.pollLast());
		System.out.println(strings);
	}
}

public class LinkedList_Tests {
	public static void main(String[] args) {
		LinkedListTestser tester = new LinkedListTestser();

		// tester.Poll();
		tester.Pollast();

	}
}
