/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* StaticImportTests class
*
* @name    : StaticImportTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Some_Month 01, 2020
****************************************************************************/ 

package Import;

import static Import.classes.PhysicalConstants.AVOGADROS_NUMBER;

public class StaticImportTests {
	public static void main(String[] args)
	{
		// We can access AVOGADROS_NUMBER directly without PhysicalConstants
		System.out.println(AVOGADROS_NUMBER);
	}
}
