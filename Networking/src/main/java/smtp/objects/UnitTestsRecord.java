//============================================================================
// Name        : UnitTestsRecord.java
// Created on  : September 02, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : UnitTestsRecord class.  
//				 Implements the UnitTests database table entry.
//============================================================================

package smtp.objects;

/** @author a.tokmakov **/
/** @class  UnitTestsRecord. **/
public class UnitTestsRecord extends TableEntry {
	/* Worker name: */
	protected String worker_name = "";
	/* Unit test results text content.
	 * Text presented in JSON format . */
	protected String result_json_text = "";
	
	/** UnitTestsRecord class default constructor. **/
	public UnitTestsRecord() {
		this.id = -1;
	}
	
	/** @return the worker name. **/
	public String getWorkerName() {
		return this.worker_name;
	}
	
	/**  @param sets the worker name. **/
	public void setWorkerName(final String workerName) {
		this.worker_name = workerName;
	}
	
	/** @return the result json text. **/
	public String getResultJson() {
		return this.result_json_text;
	}
	
	/**  @param sets the result_json_text. **/
	public void setResultJson(final String resultJson) {
		this.result_json_text = resultJson;
	}
	
	/** Overrides toString() Object method:  **/
	@Override 
	public String toString() {
		return "{ id : " + this.id  + 
				", uuid : "  + this.uuid + 
				", worker_name : "  + this.worker_name + 
				",\n start_time : " + this.start_time + 
				", end_time : " + this.end_time + 
				",\n results : " + this.result_json_text + "}";
	}
	
	/** Overrides equals() Object method:  **/
	@Override 
	public boolean equals(Object obj) {
		UnitTestsRecord toCompareWith = (UnitTestsRecord)obj;
		if (this.getId() != toCompareWith.getId())
			return false;
		if (0 != this.getUuid().compareTo(toCompareWith.getUuid()))
			return false;
		if (0 != this.getWorkerName().compareTo(toCompareWith.getWorkerName()))
			return false;		
		if (0 != this.getStartTime().compareTo(toCompareWith.getStartTime()))
			return false;
		if (0 != this.getEndTime().compareTo(toCompareWith.getEndTime()))
			return false;
		/*
		 TODO: Do we realy need this ??? 
		 
		 if (0 != this.getResultJson().compareTo(toCompareWith.getResultJson()))
			return false;
		*/
		return true;
	}
}
