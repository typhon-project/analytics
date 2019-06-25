package com.alphabank.typhon.extractor.delete;

import net.sf.jsqlparser.JSQLParserException;

public class NonFinancialEventDeleteExtractor extends DeleteExtractor {

	public NonFinancialEventDeleteExtractor(String query)
			throws JSQLParserException {
		super(query);
		// TODO Auto-generated constructor stub
	}

	public String extractFinancialEventId() {

		String columnName = parsedStatement.getWhere().getASTNode()
				.jjtGetFirstToken().toString();
		String columnValue = parsedStatement.getWhere().getASTNode()
				.jjtGetLastToken().toString();

		return columnValue;
	}

	public String retrieveAccountNumber() {

		System.out.println(parsedStatement);
		return "DeleteAccountNumber55555544444444";

	}

}
