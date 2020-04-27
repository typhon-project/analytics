package org.typhon.commands;

import java.util.ArrayList;

import org.typhon.entities.Entity;

public class Delete {

	ArrayList<Entity> deletedEntities;

	public ArrayList<Entity> getDeletedEntities() {
		return deletedEntities;
	}

	public void setDeletedEntities(ArrayList<Entity> deletedEntities) {
		this.deletedEntities = deletedEntities;
	}

}
