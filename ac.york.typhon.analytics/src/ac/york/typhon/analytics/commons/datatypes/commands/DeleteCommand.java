package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;

import net.sf.jsqlparser.statement.delete.Delete;

import com.fasterxml.jackson.annotation.JsonTypeName;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

@JsonTypeName("delete")
public class DeleteCommand extends DMLCommand {

	ArrayList<Entity> deletedEntities;

	public ArrayList<Entity> getDeletedEntities() {
		return deletedEntities;
	}

	public void setDeletedEntities(ArrayList<Entity> deletedEntities) {
		this.deletedEntities = deletedEntities;
	}

	@Override
	public void populateFromQLStatement(String query) {
		//TODO: Implement this
	}

	@Override
	public String toString() {
		return "DeleteCommand [deletedEntities=" + deletedEntities + ", piles="
				+ entities + ", columns=" + columns + ", clause=" + clause
				+ ", targetDb=" + targetDb + "]";
	}

}
