package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;

public class UpdateCommand extends DMLCommand {

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

	@Override
	public void populateFromSqlStatement(String sql) {
		// Use this function to populate Update related fields

		this.populatePilesFromSqlStatement(sql);
		System.out.println("Event: Update  " + this.piles);

	}
}
