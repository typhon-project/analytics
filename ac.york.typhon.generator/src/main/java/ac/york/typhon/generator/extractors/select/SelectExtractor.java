package ac.york.typhon.generator.extractors.select;

import java.util.HashMap;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.Select;

public abstract class SelectExtractor {

	protected Select parsedStatement;

	public SelectExtractor(String query) throws JSQLParserException {

		parsedStatement = (Select) CCJSqlParserUtil.parse(query);

	}

	protected HashMap<String, String> populateFieldValueMap() {

		List<Column> columnsList = null; //parsedStatement.getColumns();

		List<Expression> expressionList = null; // parsedStatement.getExpressions();

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
