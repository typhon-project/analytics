package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;

import com.fasterxml.jackson.annotation.JsonTypeName;

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

	ArrayList<Entity> insertedEntities;
	Map<String, String> columnValueMap;

	public ArrayList<Entity> getInsertedEntities() {
		return insertedEntities;
	}

	public void setInsertedEntities(ArrayList<Entity> insertedEntities) {
		this.insertedEntities = insertedEntities;
	}

	public Map<String, String> getColumnValueMap() {
		return columnValueMap;
	}

	public void setColumnValueMap(Map<String, String> columnValueMap) {
		this.columnValueMap = columnValueMap;
	}

	@Override
	public void populateFromSqlStatement(String sql) {
		// Use this function to populate Insert related fields

		Insert statement = (Insert) this.populatePilesFromSqlStatement(sql);

		ExpressionList expressionList = (ExpressionList) statement
				.getItemsList();
		List<Expression> values = expressionList.getExpressions();
		columnValueMap = new HashMap<String, String>();
		for (int i = 0; i < expressionList.getExpressions().size(); i++) {
			columnValueMap.put(statement.getColumns().get(i).getColumnName(),
					values.get(i).toString());
			System.out.println(values.get(i));
		}

	}

	@Override
	public String toString() {
		return "InsertCommand [insertedEntities=" + insertedEntities
				+ ", columnValueMap=" + columnValueMap + ", piles=" + piles
				+ ", columns=" + columns + ", clause=" + clause + ", targetDb="
				+ targetDb + "]";
	}

}
