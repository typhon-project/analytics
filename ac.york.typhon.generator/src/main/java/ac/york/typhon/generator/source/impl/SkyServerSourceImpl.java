package ac.york.typhon.generator.source.impl;

import java.io.FileNotFoundException;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.TopicPublisher;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.ISource;

public class SkyServerSourceImpl extends SourceImpl implements ISource {

	@Override
	public void generate() throws Exception {
		try {

			Iterable<CSVRecord> records = retrieveRecordsIterator(GeneratorConstants.FileNames.SQL_LOG_FILE_NAME);

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

					String dateStr = year + "-" + month + "-" + day + " " + hour
							+ ":" + minutes + ":" + seconds;
					
					Timestamp timestamp = Utils.convertStringToTimeStamp(dateStr);

					Thread.sleep(RandomUtils.nextLong(0, 1000));

					Event preEvent = new PreEvent(id, query, user,
							timestamp, dbUser);

					TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

					Thread.sleep(RandomUtils.nextLong(0, 2000));

				}
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}
	}
}
