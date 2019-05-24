package ac.uk.york.typhon.query.generator.source.impl;

import java.io.FileNotFoundException;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.TopicPublisher;
import ac.uk.york.typhon.query.generator.helper.Utils;
import ac.uk.york.typhon.query.generator.source.ISource;
import ac.uk.york.typhon.query.generator.sqlbuilder.StatementFactory;

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
