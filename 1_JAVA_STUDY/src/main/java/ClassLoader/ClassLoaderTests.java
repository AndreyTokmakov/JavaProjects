/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ClassLoaderTests.java class
*
* @name    : ClassLoaderTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 25, 2020
****************************************************************************/

package ClassLoader;

class SomeTestClass {
}

public class ClassLoaderTests
{
	public static void main(String[] args)
	{
		ClassLoader loader = ClassLoaderTests.class.getClassLoader();
		System.out.println();
		System.out.println(loader.getResource(SomeTestClass.class.getTypeName().replace('.', '/') + ".class"));
	}
}
