package ac.york.typhon.generator.extractors.update;

import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.update.Update;

public abstract class UpdateExtractor {

	protected Update parsedStatement;

	public UpdateExtractor(String query) throws JSQLParserException {

		parsedStatement = (Update) CCJSqlParserUtil.parse(query);

	}

	protected HashMap<String, String> populateFieldValueMap() {

		List<Column> columnsList = parsedStatement.getColumns();

		List<Expression> expressionList = parsedStatement.getExpressions();
		
		HashMap<String, String> fieldValueMap = new HashMap<String, String>();
		for (int i = 0; i < columnsList.size(); i++) {
			// System.out.println(columnsList.get(i));
			// System.out.println(expressionList.get(i));

			fieldValueMap.put(
					columnsList.get(i).getColumnName(),
					expressionList.get(i).toString().trim()
							.replaceAll("(^')|('$)", ""));

		}

		return fieldValueMap;
	}

}
