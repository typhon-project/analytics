package ac.uk.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;

public class Insert extends DMLCommand {

	ArrayList<Entity> insertedEntities;

	public ArrayList<Entity> getInsertedEntities() {
		return insertedEntities;
	}

	public void setInsertedEntities(ArrayList<Entity> insertedEntities) {
		this.insertedEntities = insertedEntities;
	}

	@Override
	public void populateFromSqlStatement(String sql) {
		// Use this function to populate Insert related fields

		this.populatePilesFromSqlStatement(sql);
		System.out.println("Event: Insert  " + this.piles);

	}
}
