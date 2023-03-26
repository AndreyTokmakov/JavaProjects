/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* EnumSingletons.java class
*
* @name    : EnumSingletons.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 17, 2020
****************************************************************************/

package Enums;

enum MySingleton {	
    INSTANCE;
	
    private MySingleton() 
    {
        System.out.println("MySingleton instance created!!");
    }
}

public class EnumSingletons {
	
	public static void main(String[] args) 
	{
	    System.out.println(MySingleton.INSTANCE);
	    System.out.println(MySingleton.INSTANCE);
	    System.out.println(MySingleton.INSTANCE);
	    System.out.println(MySingleton.INSTANCE);
	}
}
