/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Objects_IdentifyHashCode.java class
*
* @name    : Objects_IdentifyHashCode.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 5, 2020
****************************************************************************/

package memory;

public class Objects_IdentifyHashCode {
	public static void main(String[] args)
	{
		Integer intVar1 = 1;
		Integer intVar2 = 2;
		Integer intVar3 = 1;
		
		System.out.println(System.identityHashCode(intVar1));
		System.out.println(System.identityHashCode(intVar2));
		System.out.println(System.identityHashCode(intVar3));
	}
}
