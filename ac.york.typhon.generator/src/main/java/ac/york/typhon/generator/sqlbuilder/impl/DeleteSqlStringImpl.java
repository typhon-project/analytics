package ac.york.typhon.generator.sqlbuilder.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.york.typhon.generator.sqlbuilder.ISqlString;

public class DeleteSqlStringImpl extends SqlStringImpl implements ISqlString {

	public DeleteSqlStringImpl(String tableName, CSVRecord record) {
		super(tableName, record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			// DELETE FROM table_name WHERE condition;

			this.sqlStatement = "delete from "+ tableName +" where "
					+ columnsList.get(0) + " = " + valuesList.get(0);

		}

		return this.sqlStatement;
	}

	@Override
	public String toString() {
		return "DeleteSqlStringImpl [sqlStatement=" + sqlStatement
				+ ", columnsList=" + columnsList + ", valuesList=" + valuesList
				+ "]";
	}

}
