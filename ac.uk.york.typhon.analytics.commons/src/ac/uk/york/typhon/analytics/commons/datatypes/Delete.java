package ac.uk.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;

public class Delete extends DMLCommand {

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

}
