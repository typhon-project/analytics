package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeName;

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
	public void populateFromSqlStatement(String sql) {
		// Use this function to populate Delete related fields

		this.populatePilesFromSqlStatement(sql);
		System.out.println("Event: Delete  " + this.piles);

	}

	@Override
	public String toString() {
		return "DeleteCommand [deletedEntities=" + deletedEntities + ", piles="
				+ piles + ", columns=" + columns + ", clause=" + clause
				+ ", targetDb=" + targetDb + "]";
	}

}
