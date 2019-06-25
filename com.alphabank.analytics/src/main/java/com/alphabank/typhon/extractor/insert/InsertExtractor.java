package com.alphabank.typhon.extractor.insert;

import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;

public abstract class InsertExtractor {

	protected Insert parsedStatement;

	// static {
	// registerClassForFactory();
	// }
	//
	// public static void registerClassForFactory() {
	// ParsedStatementFactory.register(StatementType.INSERT,
	// InsertExtractor.class);
	// }

	// public InsertExtractor(InsertParsedStatement parsedStatement) {
	// this.parsedStatement = parsedStatement.getStatement();
	//
	// }

	public InsertExtractor(String query) throws JSQLParserException {

		parsedStatement = (Insert) CCJSqlParserUtil.parse(query);

	}

	protected HashMap<String, String> populateFieldValueMap() {

		List<Column> columnsList = parsedStatement.getColumns();

		List<Expression> expressionList = ((ExpressionList) parsedStatement
				.getItemsList()).getExpressions();

		HashMap<String, String> fieldValueMap = new HashMap<String, String>();
		for (int i = 0; i < columnsList.size(); i++) {
//			System.out.println(columnsList.get(i));
//			System.out.println(expressionList.get(i));

			fieldValueMap.put(
					columnsList.get(i).getColumnName(),
					expressionList.get(i).toString().trim()
							.replaceAll("(^')|('$)", ""));

		}

		return fieldValueMap;
	}
}
