package ac.uk.york.typhon.analytics.commons.datatypes.events;

public abstract class Event {

	protected String id;
	protected String query;

	public Event() {

	}

	public Event(String id, String query) {
		super();
		this.id = id;
		this.query = query;
	}

	public String getId() {
		return id;
	}

	public String getQuery() {
		return query;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", query=" + query + "]";
	}

	// public String toJson() {
	// return new Gson().toJson(this);
	// }

}
