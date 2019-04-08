package ac.uk.york.typhon.query.generator.sqlbuilder.impl;

import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;

import ac.uk.york.typhon.query.generator.sqlbuilder.ISqlString;

public class UpdateSqlStringImpl extends SqlStringImpl implements ISqlString {

	public UpdateSqlStringImpl(CSVRecord record) {
		super(record);
	}

	public String build() {

		if (CollectionUtils.isNotEmpty(columnsList)) {

			// UPDATE table_name
			// SET column1 = value1, column2 = value2, ...
			// WHERE condition;

			Iterator<String> columnsIterator = columnsList.iterator();
			Iterator<String> valuesIterator = valuesList.iterator();

			// ignore the first column and its value as they will be used in
			// the where clause assuming it is the primary key
			columnsIterator.next();
			valuesIterator.next();

			String updateClause = "";
			while (columnsIterator.hasNext() && valuesIterator.hasNext()) {

				updateClause += columnsIterator.next() + " = "
						+ valuesIterator.next();

				if (columnsIterator.hasNext() && valuesIterator.hasNext()) {
					updateClause += " , ";
				}

			}

			this.sqlStatement = "update TABLE_NAME set " + updateClause
					+ " where " + columnsList.get(0) + " = "
					+ valuesList.get(0);

		}

		return this.sqlStatement;
	}

	@Override
	public String toString() {
		return "UpdateSqlStringImpl [sqlStatement=" + sqlStatement
				+ ", columnsList=" + columnsList + ", valuesList=" + valuesList
				+ "]";
	}

}
