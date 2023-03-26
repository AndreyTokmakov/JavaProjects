/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Function_References.java class
*
* @name    : Function_References.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : December 3, 2020
****************************************************************************/

package Lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


class Utils {
	public int sum(int a, int b) {
		return a + b;
	}
}

class Person {
	private String name = null;

	public Person() {
		this("Unnamed");
	}
	
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person(" + name + ")";
	}
}

class Tester {

	private List<Person> users = new ArrayList<Person>();
	
	private void fill_with_consumer(java.util.function.Consumer<Person> adder) {
		final List<Person> persons = Arrays.asList(new Person("Ann"),new Person("Max"),new Person("Jonh"),new Person("Lexx"));
		persons.stream().forEach(adder);
	}
	
	protected void Add2List_AsLambda() {
		System.out.println(users);
		fill_with_consumer(users::add);
		System.out.println(users);
	}
	
	//-----------------------------------------------------------------------------//

	protected void Integer_Sum() {
		IntStream.of(1,2,3).reduce(Integer::sum).ifPresent(System.out::println); 
	}
	
	protected void Integer_Sum_Custom() {
		Utils U = new Utils();
		IntStream.of(1,2,3).reduce(U::sum).ifPresent(System.out::println); 
	}
	
	protected void System_Println() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
		list.stream().forEach(System.out::println);
	}
	
	protected void ToUpperCase() {
		List<String> mList = Arrays.asList("aa1","cc2", "cc1", "aa2", "bb1");
		mList.stream().map(String::toUpperCase).sorted().forEach(System.out::println);
	}
	
	protected void ParseInt() {
		Stream.of("c1", "c2", "c3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).forEach(System.out::println);
	}
}

public class Function_References {
	/** **/
	private final static Tester tester = new Tester();
	
	public static void main(String[] args) {
		
		tester.Add2List_AsLambda();
		
		// tester.Integer_Sum_Custom();
		
		// tester.Integer_Sum();
		// tester.System_Println();
		// tester.ToUpperCase();
		// tester.ParseInt();
	}
}
