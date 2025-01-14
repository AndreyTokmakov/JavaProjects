/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* LazyInitialization_Test.java class
*
* @name    : LazyInitialization_Test.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Singleton;

public class LazyInitialization
{
	private final static class SingletonImpl
	{
		private static SingletonImpl INSTANCE = null;

		private SingletonImpl() {
			System.out.println(this.getClass().getName() + " created");
		}

		public static SingletonImpl getInstance()
		{
			if (INSTANCE == null) {
				synchronized (SingletonImpl.class) {
					if (INSTANCE == null) {
						INSTANCE = new SingletonImpl();
					}
				}
			}
			return INSTANCE;
		}

		@Override
		public String toString() {
			return "Hello from '" + this.getClass().getName() + "'";
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
