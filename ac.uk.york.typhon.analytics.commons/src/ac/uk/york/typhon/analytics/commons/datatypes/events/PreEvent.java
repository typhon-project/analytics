package ac.uk.york.typhon.analytics.commons.datatypes.events;

import java.io.Serializable;
import java.util.Date;

public class PreEvent extends Event implements Serializable {

	String user;
	Date queryTime;
	String dbUser;
	boolean authenticated;

	public PreEvent() {
		super();
	}

	public PreEvent(String id, String query, String user, Date queryTime,
			String dbUser) {
		super(id, query);
		this.user = user;
		this.queryTime = queryTime;
		this.dbUser = dbUser;
		this.authenticated = false;
	}

	public String getUser() {
		return user;
	}

	public Date getQueryTime() {
		return queryTime;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
	public String toString() {
		return "PreEvent [user=" + user + ", queryTime=" + queryTime
				+ ", dbUser=" + dbUser + ", authenticated=" + authenticated
				+ ", id=" + id + ", query=" + query + "]";
	}



}
