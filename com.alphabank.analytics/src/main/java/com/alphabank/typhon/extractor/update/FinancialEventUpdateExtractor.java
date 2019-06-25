package com.alphabank.typhon.extractor.update;

import net.sf.jsqlparser.JSQLParserException;

public class FinancialEventUpdateExtractor extends UpdateExtractor {

	public FinancialEventUpdateExtractor(String query)
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
		return "UpdateAccountNumber999996666";

	}
}
