/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Utilities tests 
*
* @name    : Utilities.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 05, 2020
****************************************************************************/ 

package Utilities;

/** Utilities class. **/
public class Utilities {
	
	/** Sleep : **/
	public static void sleep(long milliseconds) {
		try {
			Thread.currentThread();
			Thread.sleep(milliseconds);
		} catch (final InterruptedException exc) {
			System.err.println(exc.getMessage());
		}	
	}
}
