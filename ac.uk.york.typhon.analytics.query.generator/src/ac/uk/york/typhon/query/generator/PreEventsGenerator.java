package ac.uk.york.typhon.query.generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import ac.uk.york.typhon.analytics.topic.TopicPublisher;

import ac.uk.york.typhon.analytics.commons.datatypes.PreEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.Event;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;

import ac.uk.york.typhon.query.generator.helper.GeneratorConstants;
import ac.uk.york.typhon.query.generator.sqlbuilder.StatementFactory;

import com.google.common.io.Resources;

/*
 * This class is to simulate TyphonQL generated PRE events
 */

public class PreEventsGenerator {

	private static Random random = new Random();

	public static String generateRandomId() {

		return String.valueOf(Math.abs(random.nextLong()));
	}

	public static Timestamp generateTimeStamp() {

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);

		return ts;
	}

	public static void generate() throws Exception {
		try {

			// load events from the CSV files

			URL url = Resources.getResource(GeneratorConstants.CSV.FILE_NAME);

			// URL url = ClassLoaderUtil.getResource(
			// GeneratorConstants.CSV.FILE_NAME, PreEventsGenerator.class);

			Path path = Paths.get(url.toURI());

			Reader reader = new FileReader(path.toString());
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(
					reader);

			String id = null;

			for (CSVRecord record : records) {

				String query = StatementFactory.initializeDMLString(record);

				if (StringUtils.isNotBlank(query)) {

					id = generateRandomId();

					Thread.sleep(RandomUtils.nextLong(0, 1000));

					Event preEvent = new PreEvent(id, query, "user",
							generateTimeStamp(), "dbUser");

					TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

					Thread.sleep(RandomUtils.nextLong(0, 2000));

				}
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		// continuous load of events from the CSV and send them to the database
		// and the message queue.
		while (true) {

			PreEventsGenerator.generate();
		}

	}

}
