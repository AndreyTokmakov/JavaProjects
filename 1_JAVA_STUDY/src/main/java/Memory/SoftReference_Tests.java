/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* SoftReferencetests
*
* @name    : SoftReference_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 30, 2020
****************************************************************************/ 

package Memory;

import java.lang.ref.SoftReference;

import Memory.Utilities.GCUtils;

public class SoftReference_Tests {
	public static void main(String[] args) 
	{
	    String instance = new String("InitialValue");
	    SoftReference<String> softReference = new SoftReference<>(instance);
	    instance = null;
	    
	    System.out.println("softReference      : " + softReference);
	    System.out.println("softReference get(): " + softReference.get());
	    
	    GCUtils.tryToAllocateAllAvailableMemory(); 
	    System.out.println("softReference get(): " + softReference.get());
	}
}
