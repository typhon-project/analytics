package ac.york.typhon.analytics.commons.datatypes.events;

import ac.york.typhon.analytics.commons.enums.StatementType;

public abstract class Event {

	protected String eventId;
	protected String query;

	public Event() {

	}

	public Event(String eventId, String query) {

		this.eventId = eventId;
		this.query = query;
	}

	public StatementType retrieveStatementType() {

		// TODO use Regex instead of split
//		System.out.println("getStatementType ############################ got called ");
		return StatementType.valueOf(query.split(" ")[0].toUpperCase().trim());

	}

	public String getId() {
		return eventId;
	}

	public String getQuery() {
		return query;
	}

	public void setId(String id) {
		this.eventId = id;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "Event [id=" + eventId + ", query=" + query + "]";
	}

}
