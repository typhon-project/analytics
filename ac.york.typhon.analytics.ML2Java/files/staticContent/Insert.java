package org.typhon.commands;

import java.util.ArrayList;

import org.typhon.entities.Entity;

public class Insert extends DMLCommand {
	
ArrayList<Entity> insertedEntities;
	
	public ArrayList<Entity> getInsertedEntities() {
		return insertedEntities;
	}
	
	public void setInsertedEntities(ArrayList<Entity> insertedEntities) {
		this.insertedEntities = insertedEntities;
	}


}
