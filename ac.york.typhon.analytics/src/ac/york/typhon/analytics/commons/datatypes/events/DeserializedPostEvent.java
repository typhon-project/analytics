package ac.york.typhon.analytics.commons.datatypes.events;

import java.util.LinkedList;
import java.util.List;

import ac.york.typhon.analytics.commons.datatypes.commands.DMLCommand;

public class DeserializedPostEvent extends PostEvent {

	private List<DMLCommand> commands;
	private JSONQuery jsonquery;
	
	public DeserializedPostEvent(PostEvent event) {
		super(event.eventId, event.query, event.getSuccess(), event.getStartTime(), event.getEndTime(),
				event.getPreEvent(), event.getResultSet(), event.getInvertedQueryResultSet());
		commands = new LinkedList<DMLCommand>();
	}

	public boolean addCommand(DMLCommand c) {
		return this.commands.add(c);
	}

	public List<DMLCommand> getCommands() {
		return commands;
	}

	public void setCommands(List<DMLCommand> commands) {
		this.commands = commands;
	}

	@Override
	public String toString() {
		return "DeserializedPostEvent [success=" + getSuccess() + ", startTime=" + getStartTime() + ", endTime=" + getEndTime()
				+ ", preEvent=" + getPreEvent()
				+ ", resultSet=" + getResultSet()
				+ ", invertedResultSet=" + getInvertedQueryResultSet()
				+ ", id=" + eventId + ", query=" + query + "\ncommands=" + commands
				+ "]";
	}

	public JSONQuery getJsonquery() {
		return jsonquery;
	}

	public void setJsonquery(JSONQuery jsonquery) {
		this.jsonquery = jsonquery;
	}

}
