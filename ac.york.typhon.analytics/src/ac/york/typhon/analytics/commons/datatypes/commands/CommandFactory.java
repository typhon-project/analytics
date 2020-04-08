package ac.york.typhon.analytics.commons.datatypes.commands;

import java.util.HashMap;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import ac.york.typhon.analytics.commons.enums.StatementType;

public class CommandFactory {

	private static Map<StatementType, Class<? extends DMLCommand>> statementTypeWrapperClassMap = new HashMap<StatementType, Class<? extends DMLCommand>>();

	static {
		registerParsedStatements();
	}

	public static void registerParsedStatements() {

		CommandFactory.register(StatementType.DELETE,
				DeleteCommand.class);
		CommandFactory.register(StatementType.INSERT,
				InsertCommand.class);
		CommandFactory.register(StatementType.SELECT,
				SelectCommand.class);
		CommandFactory.register(StatementType.UPDATE,
				UpdateCommand.class);

	}

	public static void register(StatementType statementType,
			Class<? extends DMLCommand> statementWrapperClass) {
		statementTypeWrapperClassMap.put(statementType, statementWrapperClass);

	}

	private static Class<? extends DMLCommand> retrieveParsedStatementClass(
			Statement statement) throws InstantiationException,
			IllegalAccessException {

		String parsedStatmentName = statement.getClass().getSimpleName()
				.toUpperCase();
		Class<? extends DMLCommand> parsedStatementClass = statementTypeWrapperClassMap
				.get(StatementType.valueOf(parsedStatmentName));
		return parsedStatementClass;

	}


	public static DMLCommand getInstance(String query)
			throws InstantiationException, IllegalAccessException {
		DMLCommand dmlCommand = null;

		try {
			Statement statement = CCJSqlParserUtil.parse(query);
			
			dmlCommand = retrieveParsedStatementClass(statement)
					.newInstance();
//			dmlCommand.setStatement(statement);

		} catch (JSQLParserException e) {

			e.printStackTrace();
		}
		return dmlCommand;
	}

}
