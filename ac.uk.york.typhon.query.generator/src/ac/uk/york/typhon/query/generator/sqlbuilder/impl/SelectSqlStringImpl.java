package ac.uk.york.typhon.query.generator.sqlbuilder.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.uk.york.typhon.query.generator.helper.Utils;
import ac.uk.york.typhon.query.generator.sqlbuilder.ISqlString;

public class SelectSqlStringImpl extends SqlStringImpl implements ISqlString {

	public SelectSqlStringImpl(CSVRecord record) {
		super(record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			String columnsStr = Utils.removeSquareBrackets(columnsList
					.toString());
//			String valuesStr = Utils
//					.removeSquareBrackets(valuesList.toString());

			this.sqlStatement = "select " + columnsStr
					+ " from TABLE_NAME where " + columnsList.get(0) + " = "
					+ valuesList.get(0);

		}

		return this.sqlStatement;
	}

	@Override
	public String toString() {
		return "SelectSqlStringImpl [sqlStatement=" + sqlStatement
				+ ", columnsList=" + columnsList + ", valuesList=" + valuesList
				+ "]";
	}

}
