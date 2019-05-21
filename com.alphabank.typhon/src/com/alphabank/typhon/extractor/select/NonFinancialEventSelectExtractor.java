package com.alphabank.typhon.extractor.select;

import net.sf.jsqlparser.JSQLParserException;

public class NonFinancialEventSelectExtractor extends SelectExtractor {
	public NonFinancialEventSelectExtractor(String query)
			throws JSQLParserException {
		super(query);

	}

	public String retrieveAccountNumber() {
		return "SelectAccount666999666999";

	}

}
