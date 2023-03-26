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

class Singleton_StaticFactoryMethod {
    private static final Singleton_StaticFactoryMethod INSTANCE = 
    		new Singleton_StaticFactoryMethod();

    private Singleton_StaticFactoryMethod() {
    	System.out.println("Singleton_Static ");
    }

    public static Singleton_StaticFactoryMethod getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "Hello from Singleton_StaticFactoryMethod";
	}
}

public class SimplestSingleton_Test {
	public static void main(String[] args) {
		Singleton_StaticFactoryMethod singleton = Singleton_StaticFactoryMethod.getInstance();
		System.out.println(singleton);
		System.out.println(Singleton_StaticFactoryMethod.getInstance());
		System.out.println(Singleton_StaticFactoryMethod.getInstance());
	}
}
