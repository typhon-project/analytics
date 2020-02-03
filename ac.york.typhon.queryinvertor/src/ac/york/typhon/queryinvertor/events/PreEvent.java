package ac.york.typhon.queryinvertor.events;

import java.util.ArrayList;
import java.util.Date;

public class PreEvent extends Event {
	
	
	String user;
	Date queryTime;
	String dbUser;
	boolean authenticated;
	ArrayList<String> additionalQueries;

	public PreEvent(String id, String query, String user, Date queryTime, String dbUser, ArrayList<String> additionalQueries) {
		super(id, query);
		this.user = user;
		this.queryTime = queryTime;
		this.dbUser = dbUser;
		this.additionalQueries = additionalQueries;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getQueryTime() {
		return queryTime;
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
	public ArrayList<String> getAdditionalQueries() {
		return additionalQueries;
	}
	public void setAdditionalQueries(ArrayList<String> additionalQueries) {
		this.additionalQueries = additionalQueries;
	}
	
	

}
