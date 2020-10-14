package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.statement.insert.Insert;

import com.fasterxml.jackson.annotation.JsonTypeName;

import ac.york.typhon.analytics.commons.datatypes.events.Entity;

@JsonTypeName("insert")
public class InsertCommand extends DMLCommand {

	// private Insert statement;
	//
	// public InsertCommand() {
	// }
	//
	// public InsertCommand(String sql) {
	// statement = (Insert) super.parseSqlStatement(sql);
	// }
	//
	// public Insert getStatement() {
	// return statement;
	// }
	//
	// @Override
	// public void setStatement(Statement statement) {
	// this.statement = (Insert) statement;
	// }
	//
	// @Override
	// public String toString() {
	// return "InsertCommand [statement=" + statement + "]";
	// }

	List<Entity> insertedEntities;

	public List<Entity> getInsertedEntities() {
		return insertedEntities;
	}

	public void setInsertedEntities(List<Entity> insertedEntities) {
		this.insertedEntities = insertedEntities;
	}
//
//	public Map<String, String> getColumnValueMap() {
//		return columnValueMap;
//	}
//
//	public void setColumnValueMap(Map<String, String> columnValueMap) {
//		this.columnValueMap = columnValueMap;
//	}

	@Override
	public String toString() {
		return "InsertCommand [insertedEntities=" + insertedEntities
				+ ", affected=" + affected
				+ ", clause=" + clause + ", targetDb="
				+ "]";
	}

}
