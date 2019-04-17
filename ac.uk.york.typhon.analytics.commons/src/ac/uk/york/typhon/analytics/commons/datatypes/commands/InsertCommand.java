package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("insert")
public class InsertCommand extends DMLCommand {

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

	@Override
	public String toString() {
		return "InsertCommand [insertedEntities=" + insertedEntities
				+ ", piles=" + piles + ", columns=" + columns + ", clause="
				+ clause + ", targetDb=" + targetDb + "]";
	}
}
