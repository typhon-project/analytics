package com.alphabank.typhon.extractor.update;

import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.SelectParsedStatement;
import ac.uk.york.typhon.analytics.commons.datatypes.wrapper.parsedstatement.UpdateParsedStatement;
import net.sf.jsqlparser.JSQLParserException;

public class UpdateExtractorFinancialEvent extends UpdateExtractor {

	public UpdateExtractorFinancialEvent(String query)
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
