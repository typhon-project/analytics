package com.alphabank.typhon.extractor.select;

import net.sf.jsqlparser.JSQLParserException;
import ac.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.SelectParsedStatement;

public class FinancialEventSelectExtractor extends SelectExtractor {

	public FinancialEventSelectExtractor(String query)
			throws JSQLParserException {
		super(query);

	}

	public String extractFinancialEventId() {

		// String columnName = parsedStatement.getWhere().getASTNode()
		// .jjtGetFirstToken().toString();
		// String columnValue = parsedStatement.getWhere().getASTNode()
		// .jjtGetLastToken().toString();

		return "FinancialEventId";
	}

	public String retrieveAccountNumber() {

		System.out.println(parsedStatement);
		return "SelectAccountNumber55555544444444";

	}

	public static String retrieveTransactionId(
			SelectParsedStatement parsedStatement) {
		return "SelectTransactionID0909090";

	}
}
