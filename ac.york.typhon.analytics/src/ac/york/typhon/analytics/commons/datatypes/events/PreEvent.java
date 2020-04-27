package ac.york.typhon.analytics.commons.datatypes.events;

import java.io.Serializable;
import java.util.Date;

public class PreEvent extends Event implements Serializable {

	String user;
	Date queryTime;
	String dbUser;
	boolean authenticated;
	String invertedQuery;
	boolean invertedNeeded;

	public boolean isInvertedNeeded() {
		return invertedNeeded;
	}

	public void setInvertedNeeded(boolean invertedNeeded) {
		this.invertedNeeded = invertedNeeded;
	}

	public PreEvent() {
		super();
	}

	public PreEvent(String id, String query, String user, Date queryTime,
			String dbUser, String invertedQuery) {
		super(id, query);
		this.user = user;
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

	public String getUser() {
		return user;
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

	public void setUser(String user) {
		this.user = user;
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
		return "PreEvent [user=" + user + ", queryTime=" + queryTime
				+ ", dbUser=" + dbUser + ", authenticated=" + authenticated
				+ ", id=" + eventId + ", query="
				+ query + ", invertedQuery=" + invertedQuery
				+ ", invertedNeeded=" + invertedNeeded + "]";
	}

}
