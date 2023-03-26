/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CallBaseClassConstructor.java class
*
* @name    : CallBaseClassConstructor.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 6, 2021
****************************************************************************/

package ObjectOrientedProgramming.Constructors;


class Base {
	public Base(String str) {
		System.out.println("Base(" + str + ") called." );
	}
}

class Derived extends Base {
	
	public Derived(String str1, String str2) {
		super(str1);
		System.out.println("Derived(" + str1 + ", " + str2  + ") called." );
		// super(str1); - Can not be called herer
	}
}

public class CallBaseClassConstructor {

	public static void main(String[] args)
	{
		Derived d = new Derived("Param1", "Param2");
	}
}
