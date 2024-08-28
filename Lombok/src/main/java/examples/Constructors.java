/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Constructors.java class
*
* @name    : Constructors.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package examples;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ToString
@RequiredArgsConstructor()
class ConstructorExample1 {
	private int x;
	private int y;
	
	// Required in Ctor
	@NonNull 
	private String description;
	
	// Required in Ctor
	private final long code;
}


@ToString
@RequiredArgsConstructor()
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
class ConstructorExample2 {
	private int x;
	private int y;
	
	@NonNull 
	private String description;
}


@ToString
@NoArgsConstructor
class NoArgsExample
{
	@NonNull 
	private String description = "Test";
}

public class Constructors
{
	protected static void NoArgsTest() {
		NoArgsExample example = new NoArgsExample();
		
		System.out.println(example);
	}
	
	protected static void ArgsTest() {
		ConstructorExample1 example = new ConstructorExample1("DESCRIPTION TEXT VALUE", 123);
		System.out.println(example);
	}
	
	
	public static void main(String[] args) 
	{
		// NoArgsTest();
		ArgsTest();
	}
}
