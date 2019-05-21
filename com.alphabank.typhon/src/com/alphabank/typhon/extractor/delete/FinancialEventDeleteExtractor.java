package com.alphabank.typhon.extractor.delete;

import net.sf.jsqlparser.JSQLParserException;

public class FinancialEventDeleteExtractor extends DeleteExtractor {

	public FinancialEventDeleteExtractor(String query)
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

abstract class NonFinancialEventColumnName {

	

}