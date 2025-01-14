/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* StaticFactoryMethod.java class
*
* @name    : StaticFactoryMethod.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Singleton;

public class SimplestSingleton
{
	private final static class SingletonImpl
	{
		private static final SingletonImpl INSTANCE = new SingletonImpl();

		private SingletonImpl() {
			System.out.println(this.getClass().getName() + " created");
		}

		public static SingletonImpl getInstance(){
			return INSTANCE;
		}

		@Override
		public String toString() {
			return "Hello from Singleton";
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Starting application");

		SingletonImpl s2 = SingletonImpl.getInstance();
		SingletonImpl s1 = SingletonImpl.getInstance();

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s2 == s1);
	}
}
