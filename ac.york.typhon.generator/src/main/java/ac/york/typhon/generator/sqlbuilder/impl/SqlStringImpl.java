package ac.york.typhon.generator.sqlbuilder.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.csv.CSVRecord;

public abstract class SqlStringImpl {

	protected String sqlStatement;
	protected List<String> columnsList;
	protected List<String> valuesList;
	protected String tableName;

	public SqlStringImpl(String tableName, CSVRecord record) {
		this.tableName = tableName;
		// SELECT column1, column2, ... FROM table_name;

		// String recorStr = record.toString();
		Map<String, String> recordMap = record.toMap();

		if (MapUtils.isNotEmpty(recordMap)) {

			columnsList = new ArrayList<String>();
			valuesList = new ArrayList<String>();
			for (Entry<String, String> field : recordMap.entrySet()) {
				columnsList.add(field.getKey());
				valuesList.add(field.getValue());

			}
		}

	}

}
