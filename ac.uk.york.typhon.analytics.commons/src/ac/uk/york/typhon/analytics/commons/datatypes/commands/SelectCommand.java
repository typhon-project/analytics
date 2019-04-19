package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("select")
public class SelectCommand extends DMLCommand {

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
//		System.out.println("Event: Select  " + this.piles);

	}

	@Override
	public String toString() {
		return "SelectCommand [returnedEntities=" + returnedEntities
				+ ", piles=" + piles + ", columns=" + columns + ", clause="
				+ clause + ", targetDb=" + targetDb + "]";
	}

}