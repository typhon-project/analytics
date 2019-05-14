package com.alphabank.typhon.extractor.select;

import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.SelectParsedStatement;
import net.sf.jsqlparser.JSQLParserException;

public class SelectExtractorFinancialEvent extends SelectExtractor {

	public SelectExtractorFinancialEvent(String query)
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
