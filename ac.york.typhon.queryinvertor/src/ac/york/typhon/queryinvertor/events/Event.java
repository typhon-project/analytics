package ac.york.typhon.queryinvertor.events;

public class Event {
	
	protected String eventId;
	protected String query;

	public Event(String eventId, String query) {

		this.eventId = eventId;
		this.query = query;
	}
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
