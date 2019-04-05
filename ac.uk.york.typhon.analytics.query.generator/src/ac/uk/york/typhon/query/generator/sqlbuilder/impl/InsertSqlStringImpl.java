package ac.uk.york.typhon.query.generator.sqlbuilder.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.uk.york.typhon.query.generator.helper.Utils;
import ac.uk.york.typhon.query.generator.sqlbuilder.ISqlString;

public class InsertSqlStringImpl extends SqlStringImpl implements ISqlString {

	public InsertSqlStringImpl(CSVRecord record) {
		super(record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			String columnsStr = Utils.removeSquareBrackets(columnsList
					.toString());
			String valuesStr = Utils
					.removeSquareBrackets(valuesList.toString());

			this.sqlStatement = "insert into TABLE_NAME (" + columnsStr
					+ ") values (" + valuesStr + ")";

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
