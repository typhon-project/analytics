package ac.uk.york.typhon.analytics.commons.datatypes;

import java.util.ArrayList;

public class Select extends DMLCommand {

	ArrayList<Entity> returnedEntities;

	public ArrayList<Entity> getReturnedEntities() {
		return returnedEntities;
	}

	public void setReturnedEntities(ArrayList<Entity> returnedEntities) {
		this.returnedEntities = returnedEntities;
	}

	public void populateFromSqlStatement(String sql) {
		// Use this function to populate Select related fields

		this.populatePilesFromSqlStatement(sql);
		System.out.println("Event: Select  " + this.piles);

	}

}