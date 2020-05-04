package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

@JsonTypeName("update")
public class UpdateCommand extends DMLCommand {

	// private Update statement;
	//
	// public UpdateCommand() {
	//
	// }
	//
	// public UpdateCommand(String sql) {
	// statement = (Update) super.parseSqlStatement(sql);
	// }
	//
	// public Update getStatement() {
	// return statement;
	// }
	//
	// public void setStatement(Statement statement) {
	// this.statement = (Update) statement;
	// }
	//
	// @Override
	// public String toString() {
	// return "UpdateCommand [statement=" + statement + "]";
	// }

	List<Entity> updatedEntities, oldEntities;

	public List<Entity> getUpdatedEntities() {
		return updatedEntities;
	}

	public List<Entity> getOldEntities() {
		return oldEntities;
	}

	public void setUpdatedEntities(List<Entity> updatedEntities) {
		this.updatedEntities = updatedEntities;
	}

	public void setOldEntities(ArrayList<Entity> oldEntities) {
		this.oldEntities = oldEntities;
	}

	@Override
	public String toString() {
		return "UpdateCommand [updatedEntities=" + updatedEntities
				+ ", oldEntities=" + oldEntities + ", affected=" + affected
				+ ", clause=" + clause + ", targetDb="
				+ "]";
	}
}
