//============================================================================
//Name        : AutotestsRecord.java
//Created on  : October 22, 2019
//Author      : Tokmakov Andrey
//Version     : 1.0
//Copyright   : Your copyright notice
//Description : AutotestsRecord class.  
//              Implements the Autp_Tests database table entry.
//============================================================================

package smtp.objects;

/** @author a.tokmakov **/
/** @class  AutotestsRecord. **/
public class AutotestsRecord extends TableEntry {
	/* Tests status. */	
	protected TestStatus status = TestStatus.Undefined;
	/* Tests status. */	
	protected TestType type = TestType.Undefined;
	/* Worker name: */
	protected String worker_name = "";
	/* OS name: */
	protected String os = "";	
	/* Unit test results text content.
	 * Text presented in JSON format . */
	protected String result_json_text = "";
	
	/** AutotestsRecord class default constructor. **/
	public AutotestsRecord() {
		this.id = -1;
	}
	
	/** @return the tests status. **/
	public TestStatus getTestStatus() {
		return this.status;
	}
	
	/** @param sets the tests status. **/
	public void setTestStatus(final TestStatus status) {
		this.status = status;
	}
	
	/** @return the tests type. **/
	public TestType getTestsType() {
		return this.type;
	}
	
	/** @param sets the tests type. **/
	public void setTestsType(final TestType type) {
		this.type = type;
	}
	
	/** @return the worker name. **/
	public String getWorkerName() {
		return this.worker_name;
	}
	
	/**  @param sets the worker name. **/
	public void setWorkerName(final String workerName) {
		this.worker_name = workerName;
	}

	/** @return the os. **/
	public String getOs() {
		return this.os;
	}
	
	/**  @param sets os. **/
	public void setOs(final String os) {
		this.os = os;
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
		return "{ build_id : " + this.id  + 
				", uuid : "  + this.uuid + 
				",\n status : "  + this.status + 
				", type : "  + this.type + 				
				", worker_name : "  + this.worker_name + 
				", os : "  + this.os + 
				",\n start_time : " + this.start_time + 
				", end_time : " + this.end_time + 
				",\n results : " + this.result_json_text + "}";
	}
	
	/** Overrides equals() Object method:  **/
	@Override 
	public boolean equals(Object obj) {
		AutotestsRecord toCompareWith = (AutotestsRecord)obj;
		if (this.getId() != toCompareWith.getId())
			return false;
		if (0 != this.getUuid().compareTo(toCompareWith.getUuid()))
			return false;
		if (this.getTestsType() != toCompareWith.getTestsType())
			return false;
		if (0 != this.getWorkerName().compareTo(toCompareWith.getWorkerName()))
			return false;
		if (0 != this.getStartTime().compareTo(toCompareWith.getStartTime()))
			return false;
		if (0 != this.getOs().compareTo(toCompareWith.getOs()))
			return false;
		/*
		if (0 != this.getEndTime().compareTo(toCompareWith.getEndTime()))
			return false;	
		if (this.getTestStatus() != toCompareWith.getTestStatus())
			return false;	
		if (0 != this.getResultJson().compareTo(toCompareWith.getResultJson()))
			return false;
		*/
		return true;
	}
	

}