/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* 
* Build class.
* Shall be used for testing
*
* @name   : Build.java
* @author : Tokmakov Andrey
* @version: 1.0
* @since  : October 25, 2020
****************************************************************************/ 

package Collections.Utilities;

public class Build {
	// Build id:
	protected int id = 0;
	
	// Build unique uuid:
	protected String uuid = "";

	/** **/
	public Build() {
	}

	/**
	 * @param id
	 * @param uuid
	 */
	public Build(int id, String uuid) {
		this.id = id;
		this.uuid = uuid;
	}

	/**  @return the id. **/
	public int getId() {
		return id;
	}

	/**  @param id the id to set. **/
	public void setId(int id) {
		this.id = id;
	}

	/** @return the uuid. **/
	public String getUuid() {
		return uuid;
	}

	/** @param uuis the uuis to set. **/
	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		return String.format("Build (id: %s, Uuid: %s)", this.id, this.uuid);
	}	
	
    // Overriding the equals of Person class 
	@Override
	public boolean equals(final Object right) {
		if (this == right) 
			return true;
		else if (null == right || right.getClass() != this.getClass())
			return false;
		Build build = (Build)right;
		return (this.id == build.id) &&
			   (this.uuid.equals(build.uuid));
	} 
	
	@Override
	public int hashCode() {
	   return this.uuid.hashCode() + 31 * this.id;
	}
}