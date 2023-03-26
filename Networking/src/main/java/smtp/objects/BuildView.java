//============================================================================
// Name        : BuildView.java
// Created on  : November 28, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : Build view class
//============================================================================

package smtp.objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** @author a.tokmakov **/
/** @class  BuildView. **/
public class BuildView {
	/** Associated with this build autotests list. **/
	protected List<AutotestsView> autotests = new ArrayList<AutotestsView>();
	/** Associated with this build unittests list. **/
	protected List<UnittestsView> unittests = new ArrayList<UnittestsView>();
	/** BuildRecord database entry instance. **/
	protected BuildRecord build_record = null;
	
	/** BuildView class default constructor. **/
	public BuildView() {
		//TODO: Do something
	}
	
	/** BuildView class default constructor. **/
	public BuildView(final BuildRecord rec_build) {
		this.build_record = rec_build;
	}
	
	/** @return the id. **/
	public long getId() {
		return this.build_record.getId();
	}
	
	/**  @return the uuid. **/
	public String getUuid() {
		return this.build_record.getUuid();
	}
	
	/**  @return the start_time. **/
	public LocalDateTime getStartTime() {
		return this.build_record.getStartTime();
	}
	
	/** @return the end_time. **/
	public LocalDateTime getEndTime() {
		return this.build_record.getEndTime();
	}

	/** @param end_time the end_time to set. **/
	public void setEndTime(final LocalDateTime end_time) {
		this.build_record.setEndTime(end_time);
	}

	/** @return the is_nightly. **/
	public boolean isNightly() {
		return this.build_record.isNightly();
	}
	
	/** @return the status. **/
	public BuildStatus getStatus() {
		return this.build_record.getStatus();
	}
	
	/** @param status the status to set. **/
	public void setStatus(BuildStatus status) {
		this.build_record.setStatus(status);
	}
	
	/** @return the buildnumber. **/
	public long getBuildnumber() {
		return this.build_record.getBuildnumber();
	}
	
	/** @return the browser_version. **/
	public String getBrowserVersion() {
		return this.build_record.getBrowserVersion();
	}
	
	/** @return the branch. **/
	public String getBranch() {
		return this.build_record.getBranch();
	}
	
	/** @return the owners. **/
	public List<String> getOwners() {
		return this.build_record.getOwners();
	}
	
	/** @return the repository. **/
	public String getRepository() {
		return this.build_record.getRepository();
	}

	/** @return the revision. **/
	public String getRevision() {
		return this.build_record.getRevision();
	}
	
	/** Adds the autotest. **/
	public void addAutotest(final AutotestsView autotest) {
		this.autotests.add(autotest);
	}
	
	/** Adds the autotest. **/
	public boolean removeAutotest(final AutotestsView autotest) {
		return this.autotests.remove(autotest);
	}
	
	/** @return the autotests list. **/
	public List<AutotestsView> getAutotests() {
		return this.autotests;
	}
	
	/** Adds the unittest. **/
	public void addUnittest(final UnittestsView unittest) {
		this.unittests.add(unittest);
	}	
	
	/** @return the autotests list. **/
	public List<UnittestsView> getUnittests() {
		return this.unittests;
	}
	
	/** Overrides toString() Object method:  **/
	@Override 
	public String toString() {
		return "{" + 
				"\n id : " + this.getId()  + 
				",\n uuid : "  + this.getUuid() + 
				",\n start_time : " + this.getStartTime() + 
				",\n end_time : " + this.getEndTime()  + 
				",\n is_nightly : " + this.isNightly()  + 
				",\n status : " + this.getStatus()  + 
				",\n buildnumber : " + this.getBuildnumber()  +
				",\n browser_version : " + this.getBrowserVersion()  + 
				",\n branch : " + this.getBranch()  + 
				",\n owners : " + this.getOwners() .toString() + 
				",\n repository : " + this.getRepository()  + 
				",\n revision : " + this.getRevision()  + 
				",\n Unittests count : " + this.unittests.size()  +
				",\n Unittests count : " + this.autotests.size()  +
				",\n}";
	}
}
