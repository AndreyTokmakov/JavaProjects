/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Predicate_Tests.java class
*
* @name    : Predicate_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 3, 2020
****************************************************************************/

package FunctionalInterface.Predicate;

import java.util.Objects;
import java.util.function.Predicate;

public class Predicate_Tests {
	
    public static void Negative() {
        Predicate<Integer> negative = i -> i < 0;
        System.out.println(negative.test(-6));
        System.out.println(negative.test(6));
        System.out.println(negative.test(0));
    }
	
	public static void Contains() {
        Predicate<String> containsA = t -> t.contains("A");
        Predicate<String> containsB = t -> t.contains("B");
        System.out.println(containsA.and(containsB).test("ABCD"));
    }
	
	public static void IsEvenNumber() {
		Predicate<Integer> isEvenNumber = x -> x % 2==0;
		System.out.println(isEvenNumber.test(4));
		System.out.println(isEvenNumber.test(3));
	}
	
	public static void NonNull() {
		Predicate<String> nonNullPredicate = Objects::nonNull;
		System.out.println(nonNullPredicate.test(""));
		System.out.println(nonNullPredicate.test(null));
	}
	

	
	public static void main(String[] args) 
	{
		// Negative();
		// Contains();
		// IsEvenNumber();
		NonNull();
	}
}
