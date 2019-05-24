package com.alphabank.typhon.extractor.delete;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.delete.Delete;

public abstract class DeleteExtractor {
	protected Delete parsedStatement;

	// static {
	// registerClassForFactory();
	// }
	//
	// public static void registerClassForFactory() {
	// ParsedStatementFactory.register(StatementType.DELETE,
	// DeleteExtractor.class);
	// }

	// public DeleteExtractor(DeleteParsedStatement parsedStatement) {
	// this.parsedStatement = parsedStatement.getStatement();
	// }

	public DeleteExtractor(String query) throws JSQLParserException {

		parsedStatement = (Delete) CCJSqlParserUtil.parse(query);

	}

	

}
