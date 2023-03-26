/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Weak reference tests
*
* @name    : WeakReferenceTests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 17, 2020
****************************************************************************/ 

package Memory;

import java.lang.ref.WeakReference;

public class WeakReferenceTests {
	public static void main(String[] args) {
		
		Variable var = new Variable();
		WeakReference<Variable> weakVar = new WeakReference<Variable>(var);
		var = null;
		
		System.out.println(weakVar.get());
		System.gc(); 
	}
}
