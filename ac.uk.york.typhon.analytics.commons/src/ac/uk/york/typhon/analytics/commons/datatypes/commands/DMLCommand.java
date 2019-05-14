package ac.uk.york.typhon.analytics.commons.datatypes.commands;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
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

	 List<String> piles;
	 List<String> columns;
	 String clause;
	 Database targetDb;

	 public DMLCommand() {
	 piles = new ArrayList<String>();
	 columns = new ArrayList<String>();
	
	 }

	 public abstract void populateFromSqlStatement(String sql);

	public List<String> getPiles() {
		return piles;
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

	public void setPiles(ArrayList<String> piles) {
		this.piles = piles;
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

	 protected Statement populatePilesFromSqlStatement(String sql) {
	 Statement statement = null;
	 try {
	 statement = CCJSqlParserUtil.parse(sql);
	 TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
	 List<String> tableNamesList = tablesNamesFinder
	 .getTableList(statement);
	 this.piles = tableNamesList;
	
	 } catch (JSQLParserException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	 return statement;
	 }

}