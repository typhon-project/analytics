package ac.york.typhon.analytics.commons.datatypes.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class PreEvent extends Event implements Serializable {

	ArrayList<Slot> slots = new ArrayList<Slot>();
	
	public ArrayList<Slot> getSlots() {
		return slots;
	}

	public void setSlots(ArrayList<Slot> slots) {
		this.slots = slots;
	}

	Date queryTime;
	String dbUser;
	boolean authenticated;
	String invertedQuery;
	boolean invertedNeeded;
	boolean resultSetNeeded;
	
	public boolean isResultSetNeeded() {
		return resultSetNeeded;
	}

	public void setResultSetNeeded(boolean resultSetNeeded) {
		this.resultSetNeeded = resultSetNeeded;
	}

	public boolean isInvertedNeeded() {
		return invertedNeeded;
	}

	public void setInvertedNeeded(boolean invertedNeeded) {
		this.invertedNeeded = invertedNeeded;
	}

	public PreEvent() {
		super();
	}

	public PreEvent(String id, String query, ArrayList<Slot> slots, Date queryTime,
			String dbUser, String invertedQuery) {
		super(id, query);
		this.slots = slots;
		this.queryTime = queryTime;
		this.dbUser = dbUser;
		this.authenticated = true;
		this.invertedQuery = invertedQuery;
		this.invertedNeeded = false;
	}
	
	public String getInvertedQuery() {
		return invertedQuery;
	}

	public void setInvertedQuery(String invertedQuery) {
		this.invertedQuery = invertedQuery;
	}


	public Date getQueryTime() {
		return queryTime;
	}

	public String getDbUser() {
		return dbUser;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public String toString() {
		return "PreEvent [slots=" + slots + ", queryTime=" + queryTime
				+ ", dbUser=" + dbUser + ", authenticated=" + authenticated
				+ ", id=" + eventId + ", query="
				+ query + ", invertedQuery=" + invertedQuery
				+ ", invertedNeeded=" + invertedNeeded + "]";
	}

}