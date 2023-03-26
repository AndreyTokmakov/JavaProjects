//============================================================================
//Name        : TableEntry.java
//Created on  : September 12, 2019
//Author      : Tokmakov Andrey
//Version     : 1.0
//Copyright   : Your copyright notice
//Description : TableEntry abstract class definition.
//============================================================================

package smtp.objects;

import java.time.LocalDateTime;

/** @author a.tokmakov **/
/** @class  TableEntry. **/
public abstract class TableEntry {
	/* ID. */
	protected long id = 0;		
	/* UUID value. */
	protected String uuid = "";
	/* Build start timestamp. */
	protected LocalDateTime start_time = null;
	/* Build end timestamp. */
	protected LocalDateTime end_time = null;
	
	/** @return the id. **/
	public long getId() {
		return id;
	}
	
	/** @param id the id to set. **/
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUuid() {
		// TODO Auto-generated method stub
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}
	
	/**  @return the start_time. **/
	public LocalDateTime getStartTime() {
		return this.start_time;
	}
	
	/** @param start_time the start_time to set. **/
	public void setStartTime(final LocalDateTime start_time) {
		this.start_time = start_time;
	}
	
	/** @return the end_time. **/
	public LocalDateTime getEndTime() {
		return this.end_time;
	}
	
	/** @param end_time the end_time to set. **/
	public void setEndTime(final LocalDateTime end_time) {
		this.end_time = end_time;
	}
	
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (false == TableEntry.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final TableEntry toCompareWith = (TableEntry) obj;
   
        /** If 'uuids' differs return False. **/
        if (false == toCompareWith.getUuid().equals(this.uuid))
        	return false;
        return true;
    }
}
