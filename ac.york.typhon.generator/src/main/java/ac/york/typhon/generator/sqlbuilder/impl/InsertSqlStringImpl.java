package ac.york.typhon.generator.sqlbuilder.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.sqlbuilder.ISqlString;

public class InsertSqlStringImpl extends SqlStringImpl implements ISqlString {

	public InsertSqlStringImpl(String tableName, CSVRecord record) {
		super(tableName, record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			String columnsStr = Utils.removeSquareBrackets(columnsList
					.toString());
			String valuesStr = Utils
					.removeSquareBrackets(valuesList.toString());

			this.sqlStatement = "insert into " + this.tableName + " ("
					+ columnsStr + ") values (" + valuesStr + ")";

		}

		return this.sqlStatement;
	}

	@Override
	public String toString() {
		return "InsertSqlStringImpl [sqlStatement=" + sqlStatement
				+ ", columnsList=" + columnsList + ", valuesList=" + valuesList
				+ "]";
	}

}
