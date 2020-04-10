package org.typhon.events;

import java.util.ArrayList;
import java.util.Date;

import org.typhon.commands.DMLCommand;

public class PreEvent extends Event {

	String user;
	Date queryTime;
	ArrayList<DMLCommand> commands;	

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
	
	public ArrayList<DMLCommand> getCommands() {
		return commands;
	}
	
	public void setCommands(ArrayList<DMLCommand> commands) {
		this.commands = commands;
	}

}
