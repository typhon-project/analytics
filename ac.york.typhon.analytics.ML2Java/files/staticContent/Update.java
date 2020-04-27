package org.typhon.commands;

import java.util.ArrayList;

import org.typhon.entities.Entity;

public class Update {

	ArrayList<Entity> updatedEntities, oldEntities;

	public ArrayList<Entity> getUpdatedEntities() {
		return updatedEntities;
	}

	public ArrayList<Entity> getOldEntities() {
		return oldEntities;
	}

	public void setUpdatedEntities(ArrayList<Entity> updatedEntities) {
		this.updatedEntities = updatedEntities;
	}

	public void setOldEntities(ArrayList<Entity> oldEntities) {
		this.oldEntities = oldEntities;
	}

}
