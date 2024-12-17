/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* OptionalInt_Tests.java class
*
* @name    : OptionalInt_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 10, 2021
****************************************************************************/

package Optional;

import java.util.OptionalInt;

public class OptionalInt_Tests {

	public static void main(String[] args)
	{
		OptionalInt optInt1 = null;
		
		
		System.out.println(optInt1);
		// System.out.println(optInt1.orElse(0));
		
		optInt1 = OptionalInt.of(1234);
		System.out.println(optInt1.orElse(0));		
	}
}
