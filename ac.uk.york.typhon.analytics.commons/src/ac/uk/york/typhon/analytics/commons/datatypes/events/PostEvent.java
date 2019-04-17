package ac.uk.york.typhon.analytics.commons.datatypes.events;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;

public class PostEvent extends Event {
	private Boolean success;
	private Date startTime;
	private Date endTime;
	private PreEvent preEvent;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property="type")
	@JsonSubTypes({
			@JsonSubTypes.Type(value = DeleteCommand.class, name = "delete"),
			@JsonSubTypes.Type(value = InsertCommand.class, name = "insert"),
			@JsonSubTypes.Type(value = SelectCommand.class, name = "select"),
			@JsonSubTypes.Type(value = UpdateCommand.class, name = "update"),

	})
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

	@Override
	public String toString() {
		return "PostEvent [success=" + success + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", preEvent=" + preEvent
				+ ", dmlCommand=" + dmlCommand + ", id=" + id + ", query="
				+ query + "]";
	}

}
