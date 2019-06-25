package ac.york.typhon.analytics.commons.datatypes.events;

import java.io.Serializable;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class PreEvent extends Event implements Serializable {

	String user;
	Date queryTime;
	String dbUser;
	boolean authenticated;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type______________")
	@JsonSubTypes({
			@JsonSubTypes.Type(value = DeleteCommand.class, name = "delete"),
			@JsonSubTypes.Type(value = InsertCommand.class, name = "insert"),
			@JsonSubTypes.Type(value = SelectCommand.class, name = "select"),
			@JsonSubTypes.Type(value = UpdateCommand.class, name = "update"),

	})
	private DMLCommand dmlCommand;
	
	
	

	public PreEvent() {
		super();
	}

	public PreEvent(String id, String query, String user, Date queryTime,
			String dbUser) {
		super(id, query);
		this.user = user;
		this.queryTime = queryTime;
		this.dbUser = dbUser;
		this.authenticated = true;
//		try {
//			this.dmlCommand = CommandFactory.getInstance(query);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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

	public DMLCommand getDmlCommand() {
		return dmlCommand;
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

	public void setDmlCommand(DMLCommand dmlCommand) {
		this.dmlCommand = dmlCommand;
	}

	@Override
	public String toString() {
		return "PreEvent [user=" + user + ", queryTime=" + queryTime
				+ ", dbUser=" + dbUser + ", authenticated=" + authenticated
				+ ", dmlCommand=" + dmlCommand + ", id=" + id + ", query="
				+ query + "]";
	}

}
