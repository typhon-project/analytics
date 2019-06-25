package ac.york.typhon.generator.sqlbuilder.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.sqlbuilder.ISqlString;

public class SelectSqlStringImpl extends SqlStringImpl implements ISqlString {

	public SelectSqlStringImpl(String tableName, CSVRecord record) {
		super(tableName, record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			String columnsStr = Utils.removeSquareBrackets(columnsList
					.toString());
			// String valuesStr = Utils
			// .removeSquareBrackets(valuesList.toString());

			this.sqlStatement = "select " + columnsStr + " from "
					+ this.tableName + " where " + columnsList.get(0) + " = "
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
