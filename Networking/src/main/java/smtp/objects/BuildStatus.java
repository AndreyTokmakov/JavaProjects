//============================================================================
// Name        : BuildStatus.java
// Created on  : September 02, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : BuildStatus class.
//============================================================================

package smtp.objects;

/** @author a.tokmakov   **/
/** @enum   BuildStatus. **/
/** All supported build statuses. **/
public enum BuildStatus {
	/* Undefined state. */
	Undefined,

	/* Build just started. */
	BuildStarted,
	
	/* Installers has been assembled and downleded to FTP. */
	InstallersAssembled,
	
	/* Unit tests run has been triggered. */
	TestsStarted,
	
	/* All tests completed. */
	TestsCompleted;

	/** Converts string to BuildStatus enum type object. **/
	public static BuildStatus fromString(final String enumStr) {
	    try {
	    	return BuildStatus.valueOf(enumStr);
	    } catch (Exception exc) {
	    	System.err.println(exc.getMessage());
	    	return BuildStatus.Undefined;
	    }
	}
}
