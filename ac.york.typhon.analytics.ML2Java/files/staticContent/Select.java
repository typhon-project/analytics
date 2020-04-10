package org.typhon.commands;

import java.util.ArrayList;

import org.typhon.entities.Entity;

public class Select {

	ArrayList<Entity> returnedEntities;

	public ArrayList<Entity> getReturnedEntities() {
		return returnedEntities;
	}

	public void setReturnedEntities(ArrayList<Entity> returnedEntities) {
		this.returnedEntities = returnedEntities;
	}

}
