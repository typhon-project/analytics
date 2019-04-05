package ac.uk.york.typhon.analytics.commons.datatypes;

import java.util.Date;

public class PostEvent extends Event {
	private Boolean success;
	private Date startTime;
	private Date endTime;
	private PreEvent preEvent;
	private DMLCommand dmlCommand;

	public PostEvent() {
		super();
	}

	public PostEvent(String id, String query, Boolean success, Date startTime,
			Date endTime, PreEvent preEvent) {
		super(id, query);
		this.success = success;
		this.startTime = startTime;
		this.endTime = endTime;
		this.preEvent = preEvent;

	}

	public PostEvent(String id, String query, Boolean success, Date startTime,
			Date endTime, PreEvent preEvent, DMLCommand dmlCommand) {
		super(id, query);
		this.success = success;
		this.startTime = startTime;
		this.endTime = endTime;
		this.preEvent = preEvent;
		this.dmlCommand = dmlCommand;
	}

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

	public DMLCommand getDmlCommand() {
		return dmlCommand;
	}

	public void setDmlCommand(DMLCommand dmlCommand) {
		this.dmlCommand = dmlCommand;
	}

}
