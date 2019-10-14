package com.twicky.extractors.delete;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.delete.Delete;

public abstract class DeleteExtractor {
	protected Delete parsedStatement;

	
	public DeleteExtractor(String query) throws JSQLParserException {

		parsedStatement = (Delete) CCJSqlParserUtil.parse(query);

	}

	

}
