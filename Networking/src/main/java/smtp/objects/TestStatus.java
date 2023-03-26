//============================================================================
//Name        : TestStatus.java
//Created on  : October 22, 2019
//Author      : Tokmakov Andrey
//Version     : 1.0
//Copyright   : Your copyright notice
//Description : TestStatus class.
//============================================================================

package smtp.objects;

/** @author a.tokmakov   **/
/** @enum   TestStatus. **/
/** All supported tests statuses. **/
public enum TestStatus {
	/* Undefined state. */
	Undefined,
	
	/* Tests run has been triggered. */
	TestsStarted,
	
	/* All tests completed. */
	TestsCompleted;

	/** Converts string to TestStatus enum type object. **/
	public static TestStatus fromString(final String enumStr) {
	    try {
	    	return TestStatus.valueOf(enumStr);
	    } catch (Exception exc) {
	    	System.err.println(exc.getMessage());
	    	return TestStatus.Undefined;
	    }
	}
}
