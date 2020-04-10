package org.typhon.events;

import java.util.Date;

public class PostEvent extends Event {
	
	Boolean success;
	Date startTime;
	Date endTime;
	PreEvent preEvent;
	
	public Boolean getSuccess() {
		return success;
	}
	
	public Date getStartTime() {
		return startTime;	
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public PreEvent getPreEvent() {
		return preEvent;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void setPreEvent(PreEvent preEvent) {
		this.preEvent = preEvent;
	}

}
