package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

@JsonTypeName("select")
public class SelectCommand extends DMLCommand {

	// private Select statement;
	//
	// public SelectCommand() {
	// }
	//
	// public SelectCommand(String sql) {
	// statement = (Select) super.parseSqlStatement(sql);
	// }
	//
	// public Select getStatement() {
	// return statement;
	// }
	//
	// public void setStatement(Statement statement) {
	// this.statement = (Select) statement;
	// }
	//
	// @Override
	// public String toString() {
	// return "SelectCommand [statement=" + statement + "]";
	// }

	List<Entity> returnedEntities;

	public List<Entity> getReturnedEntities() {
		return returnedEntities;
	}

	public void setReturnedEntities(List<Entity> returnedEntities) {
		this.returnedEntities = returnedEntities;
	}

	@Override
	public String toString() {
		return "SelectCommand [returnedEntities=" + returnedEntities
				+ ", affected=" + affected + ", clause="
				+ clause + "]";
	}

}