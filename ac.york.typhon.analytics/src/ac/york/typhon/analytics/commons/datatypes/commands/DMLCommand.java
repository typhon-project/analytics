package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;

public abstract class DMLCommand {
//
//	@JsonTypeInfo(use = Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
//	@JsonSubTypes({ @Type(value = Insert.class), @Type(value = Select.class),
//			@Type(value = Update.class), @Type(value = Delete.class), })
//	
//	@JsonIgnoreProperties(ignoreUnknown = true)
//	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "implementationtype")
//	@JsonSubTypes({
//			@JsonSubTypes.Type(value = Delete.class),
//			@JsonSubTypes.Type(value = Insert.class),
//			@JsonSubTypes.Type(value = Select.class),
//			@JsonSubTypes.Type(value = Update.class),
//
//	})
//	@JsonDeserialize(as = Select.class)
//	private Statement statement = null;
//	protected Statement parseSqlStatement(String sql) {
//		
//		try {
//			statement = CCJSqlParserUtil.parse(sql);
//		} catch (JSQLParserException e) {
//
//			e.printStackTrace();
//		}
//		return statement;
//	}
//
//	public abstract void setStatement(Statement statement);
//	public abstract Statement getStatement();

	// private Statement statement;

	List<String> entities;
	List<String> columns;
	String clause;
	Database targetDb;

	public DMLCommand() {
		entities = new ArrayList<String>();
		columns = new ArrayList<String>();
	}

	public abstract void populateFromQLStatement(String query);

	public List<String> getEntities() {
		return entities;
	}

	public List<String> getColumns() {
		return columns;
	}

	public String getClause() {
		return clause;
	}

	public Database getTargetDb() {
		return targetDb;
	}

	public void setEntities(ArrayList<String> entities) {
		this.entities = entities;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public void setTargetDb(Database db) {
		this.targetDb = db;
	}

}