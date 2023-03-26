//============================================================================
// Name        : PredicateTests.java
// Created on  : April 26, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : SortTest
//============================================================================

package FunctionalInterface.Predicate;

import java.util.function.Function;
import java.util.function.Predicate;

class Student {
	private String name;
	private int age;
	private String gender;
	private int marks;
	
	public Student(String name, int age, String gender){
		this.name = name;	
		this.age = age;
		this.gender = gender;
	} 
	
	public Student(String name, int age, String gender, int marks){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.marks = marks;
	}
	
	public String getName() {
		return name;
	}
  
	public int getAge() {
		return age;
	}
  
	public String getGender() {
		return gender;
	}
  
	public int getMarks() {
		return marks;
	}
  
	public  String customShow(Function<Student,String> fun){
		return fun.apply(this);
	}
  
	public String toString(){ 
		return name+" - "+ age +" - "+ gender + " - "+ marks;  
	}  
} 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class PredicateTests {
	
    public void IntPredicate() {
        final Predicate<Integer> isPositive = x -> x > 0;
        final Predicate<Integer> isEven = x -> x % 2 == 0;
         
        System.out.println(isPositive.test(5));  // ----> true
        System.out.println(isPositive.test(-7)); // ----> false

    	for(int i = 0 ; i < 5 ; i++) {
    		System.out.println("Is "+ i + " even: " + isEven.test(i));
    	}
    }	
	
	public void PasswordCheck_Predicate_Test() {
		// Is user name valid
		Predicate<String> isUserNameValid = u -> u != null && u.length() > 5 && u.length() < 10;
    	System.out.println("isUserNameValid: " + isUserNameValid.test("Mahesh")); //true

    	// Is password valid
    	Predicate<String> isPasswordValid = p -> p != null && p.length() > 8 && p.length() < 15;
    	System.out.println("isPasswordValid: " + isPasswordValid.test("Mahesh123")); //true
    	
    	// Word match
    	Predicate<String> isWordMatched = s -> s.startsWith("Mr.");
    	System.out.println("isWordMatched  : " + isWordMatched.test("Mr. Mahesh") + "\n"); //true
	}
	
	public void Predicate_AND_Test() {
		Predicate<Student> isMaleStudent = s -> s.getAge() >= 20 && "male".equals(s.getGender());
		Predicate<Student> isFemaleStudent = s -> s.getAge() > 18 && "female".equals(s.getGender());
		Predicate<Student> isStudentPassed = s -> s.getMarks() >= 33;

		final Student john = new Student("John", 22, "male", 30);
		Boolean result = isMaleStudent.and(isStudentPassed).test(john);
		System.out.println(result); //false

		final Student anna = new Student("Anna", 19, "female", 40);
		result = isFemaleStudent.and(isStudentPassed).test(anna);
		System.out.println(result); //true
	}
	
	public void Predicate_OR_Test() {
		Predicate<Student> isMaleStudent = s -> s.getAge() >= 20 && "male".equals(s.getGender());
		Predicate<Student> isFemaleStudent = s -> s.getAge() > 18 && "female".equals(s.getGender());
		Predicate<Student> isStudentPassed = s -> s.getMarks() >= 33;

		Student student1 = new Student("Mahesh", 22, "male", 35);
		//Test either male or female student
		Boolean result = isMaleStudent.or(isFemaleStudent).test(student1);
		System.out.println(result); //true
		//Is student passed, too
		result = isMaleStudent.or(isFemaleStudent).and(isStudentPassed).test(student1);
		System.out.println(result); //true
	}
	
    
	///////////////////////////// MAIN //////////////////////////////
	
	public static void main(String[] args) {
		PredicateTests tests = new PredicateTests();
		
		 tests.IntPredicate();
		// tests.PasswordCheck_Predicate_Test();
		// tests.Predicate_AND_Test();
		// tests.Predicate_OR_Test();
	}
}
