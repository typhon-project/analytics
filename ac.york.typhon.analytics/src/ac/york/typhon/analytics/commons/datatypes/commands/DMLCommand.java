package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	Map<String, List<String>> affected;
	String clause;
	Database targetDb;

	public DMLCommand() {
		affected = new HashMap<String, List<String>>();
	}

	public String getClause() {
		return clause;
	}

	public Database getTargetDb() {
		return targetDb;
	}

	public Map<String, List<String>> getAffected() {
		return affected;
	}

	public void setAffected(Map<String, List<String>> affected) {
		this.affected = affected;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public void setTargetDb(Database db) {
		this.targetDb = db;
	}

}