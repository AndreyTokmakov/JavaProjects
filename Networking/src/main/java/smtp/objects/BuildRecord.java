//============================================================================
// Name        : BuildRecord.java
// Created on  : September 02, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : BuildRecord class.  Implements the Builds database table entry.
//============================================================================

package smtp.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/** @author a.tokmakov **/
/** @class  BuildsTable. **/
public class BuildRecord extends TableEntry {
	/* 'is_nightly' flag. */
	protected boolean is_nightly = false;
	/* Build status. */	
	protected BuildStatus status = BuildStatus.Undefined;
	/* The build number. */
	protected long buildnumber = 0;	
	/* Browser version. */
	protected String browser_version = ""; 
	/* Repository branch name. */
	protected String branch = "";
	/* Repository branch name. */
	protected List<String> owners = null;
	/* Git repository url. */
	protected String repository = "";
	/* Source code change revision. */
	protected String revision = "";
	
	/** BuildRecord class default constructor. **/
	public BuildRecord() {
		this.id = -1;
		this.owners = new ArrayList<String>();
	}
	
	/** @return the is_nightly. **/
	public boolean isNightly() {
		return is_nightly;
	}
	
	/** @param is_nightly the is_nightly to set. **/
	public void setNightly(boolean is_nightly) {
		this.is_nightly = is_nightly;
	}
	
	/** @return the status. **/
	public BuildStatus getStatus() {
		return status;
	}
	
	/** @param status the status to set. **/
	public void setStatus(BuildStatus status) {
		this.status = status;
	}
	
	/** @return the buildnumber. **/
	public long getBuildnumber() {
		return buildnumber;
	}
	
	/** @param buildnumber the buildnumber to set. **/
	public void setBuildnumber(long buildnumber) {
		this.buildnumber = buildnumber;
	}
	
	/** @return the browser_version. **/
	public String getBrowserVersion() {
		return browser_version;
	}
	
	/** @param browser_version the browser_version to set. **/
	public void setBrowserVersion(String browser_version) {
		this.browser_version = browser_version;
	}
	
	/** @return the branch. **/
	public String getBranch() {
		return branch;
	}
	
	/**  @param branch the branch to set. **/
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	/** @return the owners. **/
	public List<String> getOwners() {
		return owners;
	}
	
	/** @return the owners. **/
	public String getOwnersJson() {
		StringJoiner joiner = new StringJoiner(",");
		for (final String item : owners)
			joiner.add("\"" + item + "\"");
		return "[" + joiner.toString() + "]";
	}
	
	/** @return add the owner. **/
	public void addOwner(final String owner) {
		owners.add(owner);
	}	
	
	/** @param owners the owners to set. **/
	public void setOwners(List<String> owners) {
		this.owners = owners;
	}
	
	/** @return the repository. **/
	public String getRepository() {
		return repository;
	}
	
	/** @param repository the repository to set. **/
	public void setRepository(String repository) {
		this.repository = repository;
	}
	
	/** @return the revision. **/
	public String getRevision() {
		return revision;
	}
	
	/** @param revision the revision to set. **/
	public void setRevision(String revision) {
		this.revision = revision;
	}
	
	/** Overrides toString() Object method:  **/
	@Override 
	public String toString() {
		return "{" + 
				"\n id : " + this.id  + 
				",\n uuid : "  + this.uuid + 
				",\n start_time : " + this.start_time + 
				",\n end_time : " + this.end_time + 
				",\n is_nightly : " + this.is_nightly + 
				",\n status : " + this.status + 
				",\n buildnumber : " + this.buildnumber +
				",\n browser_version : " + this.browser_version + 
				",\n branch : " + this.branch + 
				",\n owners : " + this.owners.toString() + 
				",\n repository : " + this.repository + 
				",\n revision : " + this.revision + 
				",\n}";
	}
	
	/** Overrides equals() Object method:  **/
	/*
	@Override 
	public boolean equals(Object obj) {
		TestRunResult toCompareWith = (TestRunResult)obj;
		if (this.getRunId() != toCompareWith.getRunId())
			return false;
		if (0 != this.getTestName().compareTo(toCompareWith.getTestName()))
			return false;
		if (this.getTestsPassed() != toCompareWith.getTestsPassed())
			return false;
		if (this.getTestsFailed() != toCompareWith.getTestsFailed())
			return false;
		if (this.getTestsSkipped() != toCompareWith.getTestsSkipped())
			return false;
		return true;
	}*/
}
