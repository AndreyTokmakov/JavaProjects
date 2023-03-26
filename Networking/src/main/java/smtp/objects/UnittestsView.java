//============================================================================
// Name        : UnittestsView.java
// Created on  : November 29, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Autotests view class
//============================================================================

package smtp.objects;

import java.time.LocalDateTime;
import java.util.ArrayList;

/** @author a.tokmakov **/
/** @class  UnittestsView. **/
public class UnittestsView {
	/** UnitTestsRecord database entry instance. **/
	protected UnitTestsRecord unittest_record = null;
	/** List of passed tests: **/
	protected ArrayList<String> testsPassed = new ArrayList<String>();
	/** List of failed tests: **/
	protected ArrayList<String> testsFailed = new ArrayList<String>();	
	
	/** Passed tests total number: **/
	protected long testPassedTotal = 0;
	/** Failed tests total number: **/
	protected long testFailedTotal = 0;
	/** Skipped tests total number: **/
	protected long testSkippedTotal = 0;
	
	/** UnittestsView class default constructor. **/
	public UnittestsView(final UnitTestsRecord rec_autotest, long passed, long failed, long skipped) {
		this(rec_autotest);
		this.testPassedTotal = passed;
		this.testFailedTotal = failed;
		this.testSkippedTotal = skipped;
	}
	
    /**
     * BuildView class constructor
     *
     * @param rec_autotest:
     *        UnitTestsRecord class instance
     */		
	public UnittestsView(final UnitTestsRecord rec_autotest) {
		this.unittest_record = rec_autotest;
	}
	
	public long getPassedTestsCount() {
		return this.testPassedTotal;
	}
	
	public long getFailedTestsCount() {
		return this.testFailedTotal;
	}
	
	public long getSkippeddTestsCount() {
		return this.testSkippedTotal;
	}	
	
	public String getUuid() {
		return this.unittest_record.getUuid();
	}
	
	public String getWorkerName() {
		return this.unittest_record.getWorkerName();
	}
	
	public LocalDateTime getStartTime() {
		return this.unittest_record.getStartTime();
	}
	
	public LocalDateTime getEndTime() {
		return this.unittest_record.getEndTime();
	}
	
	/** Overrides toString() Object method:  **/
	@Override 
	public String toString() {
		return "{ uuid : "  + this.getUuid() + 
				", worker_name : "  + this.getWorkerName() + 
				",\n start_time : " + this.getStartTime() + 
				", end_time : " + this.getEndTime() + "}";
				// ",\n results : " + this.result_json_text + "}";
	}
	
	/** Overrides equals() Object method:  **/
	@Override 
	public boolean equals(Object obj) {
		return this.unittest_record.equals(((UnittestsView)obj).unittest_record);
	}
}
