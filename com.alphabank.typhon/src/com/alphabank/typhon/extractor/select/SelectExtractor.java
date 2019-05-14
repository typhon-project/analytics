package com.alphabank.typhon.extractor.select;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.ParsedStatementFactory;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.InsertParsedStatement;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.SelectParsedStatement;
import ac.uk.york.typhon.analytics.commons.enums.StatementType;

public abstract class SelectExtractor {

	protected Select parsedStatement;

	// static {
	// registerClassForFactory();
	// }
	//
	// public static void registerClassForFactory() {
	// ParsedStatementFactory.register(StatementType.SELECT,
	// SelectExtractor.class);
	// }

	// public ISelectExtractor(SelectParsedStatement parsedStatement) {
	// this.parsedStatement = parsedStatement.getStatement();
	//
	// }

	// public String retrieveTransactionId() {
	// return "InsertTransactionID0909090";
	//
	// }

	public SelectExtractor(String query) throws JSQLParserException {

		parsedStatement = (Select) CCJSqlParserUtil.parse(query);

	}

}
