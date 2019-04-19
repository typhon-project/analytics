package ac.uk.york.typhon.analytics.commons.datatypes.commands;



import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;


public abstract class DMLCommand {

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

	public void populatePilesFromSqlStatement(String sql) {
		try {
			Statement statement = CCJSqlParserUtil.parse(sql);
			TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
			List<String> tableNamesList = tablesNamesFinder
					.getTableList(statement);
			this.piles = tableNamesList;

		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
