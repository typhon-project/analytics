package ac.uk.york.typhon.query.generator.sqlbuilder;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

import org.apache.commons.csv.CSVRecord;

import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.DeleteCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.commands.UpdateCommand;
import ac.uk.york.typhon.query.generator.helper.EventEnum;
import ac.uk.york.typhon.query.generator.sqlbuilder.impl.DeleteSqlStringImpl;
import ac.uk.york.typhon.query.generator.sqlbuilder.impl.InsertSqlStringImpl;
import ac.uk.york.typhon.query.generator.sqlbuilder.impl.SelectSqlStringImpl;
import ac.uk.york.typhon.query.generator.sqlbuilder.impl.UpdateSqlStringImpl;

public class StatementFactory {

	// private static String statmentString = null;
	// private static DMLCommand dmlCommand = null;

	public static String initializeDMLString(CSVRecord record) {

		EventEnum randomEventEnum = EventEnum.getRandom();

		ISqlString sqlString = null;
		switch (randomEventEnum) {
		case INSERT:
			sqlString = new InsertSqlStringImpl(record);
			// dmlCommand = new Insert();

			break;
		case SELECT:
			sqlString = new SelectSqlStringImpl(record);
			// dmlCommand = new Select();

			break;
		case UPDATE:
			sqlString = new UpdateSqlStringImpl(record);
			// dmlCommand = new Update();

			break;
		case DELETE:
			sqlString = new DeleteSqlStringImpl(record);
			// dmlCommand = new Delete();

			break;

		}

		String statmentString = sqlString.build();
		// dmlCommand.populateFromSqlStatement(statmentString);

		return statmentString;

	}

	public static DMLCommand initializeDML(String sql) {
		DMLCommand dmlCommand = null;
		Statement statement;
		try {
			statement = CCJSqlParserUtil.parse(sql);

			String crudEventName = statement.getClass().getSimpleName()
					.toUpperCase();
			// System.out.println(statement.getClass().getSimpleName());

			// ISqlString sqlString = null;
			switch (crudEventName) {
			case "INSERT":
				// sqlString = new InsertSqlStringImpl(record);
				dmlCommand = new InsertCommand();

				break;
			case "SELECT":
				// sqlString = new SelectSqlStringImpl(record);
				dmlCommand = new SelectCommand();

				break;
			case "UPDATE":
				// sqlString = new UpdateSqlStringImpl(record);
				dmlCommand = new UpdateCommand();

				break;
			case "DELETE":
				// sqlString = new DeleteSqlStringImpl(record);
				dmlCommand = new DeleteCommand();

				break;

			}

			// statmentString = sqlString.build();
			dmlCommand.populateFromSqlStatement(sql);
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dmlCommand;
	}

	// public static String getStatmentString() {
	// return statmentString;
	// }
	//
	// public static DMLCommand getDmlCommand() {
	// return dmlCommand;
	// }
	//
	// public static void setStatmentString(String statmentString) {
	// StatementFactory.statmentString = statmentString;
	// }
	//
	// public static void setDmlCommand(DMLCommand dmlCommand) {
	// StatementFactory.dmlCommand = dmlCommand;
	// }

}
