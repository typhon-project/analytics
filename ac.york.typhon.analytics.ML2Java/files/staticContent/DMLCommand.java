package org.typhon.commands;

import java.util.ArrayList;

import org.typhon.databases.Database;

public abstract class DMLCommand {
	
	ArrayList<String> piles, columns;
	String clause;
	Database targetDb;

	public ArrayList<String> getPiles() {
		return piles;
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public String getClause() {
		return clause;
	}
	
	public Database getTargetDb() {
		return targetDb;
	}
	
	public void setPiles(ArrayList<String> piles) {
		this.piles = piles;
	}
	
	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}
	
	public void setClause(String clause) {
		this.clause = clause;
	}
	
	public void setTargetDb(Database db) {
		this.targetDb = targetDb;
	}

}
