//============================================================================
// Name        : AutotestsView.java
// Created on  : November 28, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Autotests view class
//============================================================================

package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author a.tokmakov **/
/** @class  AutotestsView. **/
public class AutotestsView {
	/** AutotestsRecord database entry instance. **/
	protected AutotestsRecord autotests_record = null;
	/** List of passed tests: **/
	protected ArrayList<String> testsPassed = new ArrayList<String>();
	/** List of failed tests: **/
	protected ArrayList<String> testsFailed = new ArrayList<String>();
	/** Passed tests count: **/
	protected long passedTestCount = 0;
	/** Failed tests count: **/
	protected long failedTestCount = 0;
	
	/** AutotestsView class default constructor. **/
	public AutotestsView(final AutotestsRecord rec_autotest, long passed, long failed) {
		this(rec_autotest);
		this.passedTestCount = passed;
		this.failedTestCount = failed;
	}
	
    /**
     * BuildView class constructor
     *
     * @param rec_autotest:
     *        AutotestsRecord class instance
     */		
	public AutotestsView(final AutotestsRecord rec_autotest) {
		this.autotests_record = rec_autotest;
	}
	
	/**  @return the uuid. **/
	public String getUuid() {
		return this.autotests_record.getUuid();
	}
	
	/** @return the worker name. **/
	public String getWorkerName() {
		return this.autotests_record.getWorkerName();
	}
	
	/** @return the end_time. **/
	public LocalDateTime getEndTime() {
		return this.autotests_record.getEndTime();
	}

	/** @return the end_time. **/
	public LocalDateTime getStartTime() {
		return this.autotests_record.getStartTime();
	}
	
	/** @return the tests status. **/
	public TestStatus getTestStatus() {
		return this.autotests_record.getTestStatus();
	}

	/** @return the tests type. **/
	public TestType getTestsType() {
		return this.autotests_record.getTestsType();
	}
	
	/** @return the os. **/
	public String getOs() {
		return this.autotests_record.getOs();
	}
	
    /** Overrides toString() Object method:  **/
    @Override 
    public String toString() {
            return "{ uuid : "  + this.getUuid() + 
                 ",\n status : "  + this.getTestStatus() + 
                 ", type : "  + this.getTestsType() + 				
                 ", worker_name : "  + this.getWorkerName() + 
                 ", os : "  + this.getOs() + 
                 ",\n start_time : " + this.getStartTime() + 
                 ", end_time : " + this.getEndTime()  + "}";
            //",\n results : " + this.result_json_text + "}";
    }
	
	/** Overrides equals() Object method:  **/
	@Override 
	public boolean equals(Object obj) {
		AutotestsView toCompareWith = (AutotestsView)obj;
		return this.autotests_record.equals(toCompareWith.autotests_record);
	}
	
	
	public List<String> getPassedTests() {
		return this.testsPassed;
	}
	
	public List<String> getFailedTests() {
		return this.testsFailed;
	}
	
	public long getPassedTestsCount() {
		return this.passedTestCount;
	}
	
	public long getFailedTestsCount() {
		return this.failedTestCount;
	}
}
