/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* MethodReferences demo class
*
* @name    : MethodReferences.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 17, 2020
****************************************************************************/ 

package MethodReferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

class Transformer {
	
	public static void toUpperAndPrint(String str) {
		System.out.println(str.toUpperCase());
	}
}

public class StaticMethodsDemo {
	protected static List<String> messages = Arrays.asList("one", "two", "three");
	
	protected static void Test1() {
		messages.forEach(word -> System.out.println(word));
		messages.forEach(word -> System.out.println(StringUtils.capitalize(word)));
		messages.forEach(StringUtils::capitalize);
	} 
	
	protected static void Test2() {
		messages.forEach(word -> System.out.println(word));
		messages.forEach(Transformer::toUpperAndPrint);
	} 

	
	public static void main(String[] args) 
	{
		// Test1();
		Test2();
	}
}
