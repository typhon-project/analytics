package com.alphabank.typhon.extractor.delete;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.ParsedStatementFactory;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.DeleteParsedStatement;
import ac.uk.york.typhon.analytics.commons.enums.StatementType;

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
