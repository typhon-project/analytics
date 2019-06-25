package com.alphabank.typhon.extractor.update;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.update.Update;

public abstract class UpdateExtractor {

	Update parsedStatement;

	// static {
	// registerClassForFactory();
	// }
	//
	// public static void registerClassForFactory() {
	// ParsedStatementFactory.register(StatementType.UPDATE,
	// UpdateExtractor.class);
	// }

	public UpdateExtractor(String query) throws JSQLParserException {

		parsedStatement = (Update) CCJSqlParserUtil.parse(query);

	}

	

}
