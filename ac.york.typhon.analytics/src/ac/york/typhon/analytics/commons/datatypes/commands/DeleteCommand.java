package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

@JsonTypeName("delete")
public class DeleteCommand extends DMLCommand {

	List<Entity> deletedEntities;

	public List<Entity> getDeletedEntities() {
		return deletedEntities;
	}

	
	public void setDeletedEntities(List<Entity> deletedEntities) {
		this.deletedEntities = deletedEntities;
	}

	@Override
	public String toString() {
		return "DeleteCommand [deletedEntities=" + deletedEntities + ", affected="
				+ affected + ", clause=" + clause
				+ "]";
	}

}