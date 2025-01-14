/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* EnumSingletons.java class
*
* @name    : EnumSingletons.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Singleton;

public class EnumSingletons
{
	private enum SingletonImpl
	{
		Instance;

		SingletonImpl()
		{
			System.out.println(this.getClass().getName() + " created");
		}
	}

	public static void main(String[] args) 
	{
		System.out.println("Starting application");

		SingletonImpl s2 = SingletonImpl.Instance;
		SingletonImpl s1 = SingletonImpl.Instance;

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s2 == s1);
	}
}
