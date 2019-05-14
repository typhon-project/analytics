package com.alphabank.typhon.extractor.update;

import net.sf.jsqlparser.JSQLParserException;

public class UpdateExtractorNonFinancialEvent extends UpdateExtractor {
	public UpdateExtractorNonFinancialEvent(String query)
			throws JSQLParserException {
		super(query);

	}

	public String retrieveAccountNumber() {
		return "UpdateAccount666999666999";

	}

}
