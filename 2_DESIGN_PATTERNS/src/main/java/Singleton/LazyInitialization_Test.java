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

class LazySingleton {
	/** **/
	private static LazySingleton INSTANCE = null;

	/** LazySingleton class constructor: **/
	protected LazySingleton() {
		System.out.println("LazySingleton created");
	}


	public static LazySingleton getInstance() {
		if (INSTANCE == null) {
			synchronized (LazySingleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new LazySingleton();
				}
			}
		}
		return INSTANCE;
	}
	
	@Override
	public String toString() {
		return "Hello from Singleton_StaticFactoryMethod";
	}
}

public class LazyInitialization_Test {
	public static void main(String[] args) 
	{
		LazySingleton singleton = LazySingleton.getInstance();
		System.out.println(singleton);
		System.out.println(LazySingleton.getInstance());
		System.out.println(LazySingleton.getInstance());

	}
}
