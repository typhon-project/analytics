package com.twicky.extractors.select.extractor;

import com.twicky.extractors.select.SelectExtractor;

import net.sf.jsqlparser.JSQLParserException;

public class BlockedUserSelectExtractor extends SelectExtractor {
	public BlockedUserSelectExtractor(String query) throws JSQLParserException {
		super(query);

	}

	public String retrieveAccountNumber() {
		return "SelectAccount666999666999";

	}

}
