package ac.york.typhon.generator.generators.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.ParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.TopicPublisher;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;

public class SkyServerGeneratorImpl implements IGenerator {

	@Override
	public void generate() {

		/**
		 * // Generate PreEvents from a SQl Log of SkyServer //
		 * http://skyserver.sdss.org //
		 * http://skyserver.sdss.org/log/en/traffic/sql.asp
		 */
		try {
			// Iterable<CSVRecord> records = retrieveRecordsIterator);
			IFileSource source = new LocalFileSouceImpl(
					GeneratorConstants.FileNames.SQL_LOG_FILE_NAME);

			CSVFormat csvFormat = CSVFormat.EXCEL.withHeader();

			Iterable<CSVRecord> records = source.getRecordsIterator(csvFormat);

			for (CSVRecord record : records) {

				String query = record.get("statement");

				if (StringUtils.isNotBlank(query)) {

					String id = record.get("seq");
					String user = record.get("requestor");
					String dbUser = record.get("dbname");

					String year = record.get("yy");
					String month = record.get("mm");
					String day = record.get("dd");

					String hour = record.get("hh");
					String minutes = record.get("mi");
					String seconds = record.get("ss");

					String dateStr = year + "-" + month + "-" + day + " "
							+ hour + ":" + minutes + ":" + seconds;

					Timestamp timestamp = Utils
							.convertStringToTimeStamp(dateStr);

					Thread.sleep(RandomUtils.nextLong(0, 1000));

					Event preEvent = new PreEvent(id, query, user, timestamp,
							dbUser);

					TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

					Thread.sleep(RandomUtils.nextLong(0, 2000));

				}
			}
		} catch (ParseException | InterruptedException e1) {

			e1.printStackTrace();
		}
	}
}
