//============================================================================
// Name        : TestType.java
// Created on  : July 08, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : TestType enum
//============================================================================

package smtp.objects;

/** All supported test types. **/
public enum TestType {
	/* Undefined state. */	
	Undefined,
	
	/* BuildBot unit tests. */   	
    BuildBotTests,
    
	/* Installer tests. */     
    InstallerTests,

	/* Updater tests. */    
    UpdaterTests,
    
	/* Installer tests with antiviruses. */
    InstallerAVTests,
    	
	/* Updater tests with antiviruses. */
    UpdaterAVTests;

	/** Converts string to TestType enum type object. **/
	public static TestType fromString(final String enumStr) {
	    try {
	    	return TestType.valueOf(enumStr);
	    } catch (Exception exc) {
	    	// TODO: Do we need logs here
	    	// System.out.println(exc.getMessage());
	    	return TestType.Undefined;
	    }
	}
}
