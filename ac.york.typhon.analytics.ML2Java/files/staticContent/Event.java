package org.typhon.events;

public abstract class Event {

	String id, query;
	
	
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
	
	

}
