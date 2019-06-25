package ac.york.typhon.generator.source.impl;

import java.io.FileNotFoundException;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.TopicPublisher;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.ISource;
import ac.york.typhon.generator.sqlbuilder.StatementFactory;

public class CsvSourceImpl extends SourceImpl implements ISource {

	private String csvFileName;

	public CsvSourceImpl(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	@Override
	public void generate() throws Exception {
		try {

			// load events from the CSV files
			Iterable<CSVRecord> records = retrieveRecordsIterator(this.csvFileName);
			StatementFactory.setTableName(this.csvFileName.split("\\.")[0]);
			String id = null;

			for (CSVRecord record : records) {

				String query = StatementFactory.initializeDMLString(record);

				if (StringUtils.isNotBlank(query)) {

					id = Utils.generateRandomId();

					Thread.sleep(RandomUtils.nextLong(0, 1000));

					Event preEvent = new PreEvent(id, query, "user",
							Utils.generateTimeStamp(), "dbUser");

					TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

					Thread.sleep(RandomUtils.nextLong(0, 1000));

				}
				
				
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

	}
}