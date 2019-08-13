package ac.york.typhon.generator.generators.impl;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.TopicPublisher;
import ac.york.typhon.generator.extractors.insert.extractor.TweetInsertExtractor;
import ac.york.typhon.generator.extractors.select.extractor.TweetSelectExtractor;
import ac.york.typhon.generator.extractors.update.UpdateExtractor;
import ac.york.typhon.generator.extractors.update.extractor.TweetUpdateExtractor;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.helper.GeneratorConstants.RemoteResourceHeader;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.RemoteFileSourceImpl;

public class TwickyGeneratorImpl implements IGenerator {

	private static String user;
	private static String host;
	private static String password;
	private static int port;
	private static String filePath;

	static {
		loadConfiguration();
	}

	private static void loadConfiguration() {
		user = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.USERNAME);
		host = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.HOST);
		password = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.PASSWORD);
		port = AppConfiguration
				.getInteger(GeneratorConstants.RemoteResourceCredentials.PORT);
		filePath = AppConfiguration
				.getString(GeneratorConstants.RemoteResourceCredentials.FILE_PATH);

	}

	public static void main(String[] args) {
		TwickyGeneratorImpl generator = new TwickyGeneratorImpl();
		generator.generate();
	}

	@Override
	public void generate() {

		try {
			IFileSource source = new RemoteFileSourceImpl(
					TwickyGeneratorImpl.host, TwickyGeneratorImpl.port,
					TwickyGeneratorImpl.user, TwickyGeneratorImpl.password,
					TwickyGeneratorImpl.filePath);

			CSVFormat csvFormat = CSVFormat.ORACLE.withHeader(
					RemoteResourceHeader.EVENT_TIME,
					RemoteResourceHeader.USER_HOST,
					RemoteResourceHeader.THREAD_ID,
					RemoteResourceHeader.SERVER_ID,
					RemoteResourceHeader.COMMAND_TYPE,
					RemoteResourceHeader.ARGUMENT);

			Iterable<CSVRecord> records = source.getRecordsIterator(csvFormat);
			int labelIndex = 0;
			for (CSVRecord record : records) {
				// System.out.println(record);
				// Map<String, String > map = record.toMap();
				// // System.out.println(map.size());
				// if (map.size() != RemoteResourceHeader.getFieldsCount()) {
				// continue;
				// }

				String id = Utils.generateRandomId();
				String query = record.get(RemoteResourceHeader.ARGUMENT);
				Timestamp timestamp = Timestamp.valueOf(record
						.get(RemoteResourceHeader.EVENT_TIME));
				// Timestamp timestamp = Utils.generateTimeStamp();
				String user = record.get(RemoteResourceHeader.USER_HOST);
				Event preEvent = new PreEvent(id, query, user, timestamp,
						"dbUser");

				// System.out.println(preEvent);
				/*************************************************/
				/*********** Comment Sleep if NOT NEEDED *********/
				Thread.sleep(RandomUtils.nextLong(0, 100));
				/*************************************************/
				/*************************************************/
//				System.out.println(query);
				// if (StringUtils.contains(query, "update")
				// && StringUtils.contains(query, "Tweet")) {
				// System.out.println("In extractor");
				// System.out.println(labelIndex + " "
				// + StringEscapeUtils.unescapeJson(query));
				// TweetUpdateExtractor extractor = new TweetUpdateExtractor(
				// query);
				// System.out.println(labelIndex + " " + extractor);
				//
				// labelIndex++;
				// }

				// if (StringUtils.contains(query, "select")
				// && StringUtils.contains(query, "Tweet")) {
				// System.out.println("In extractor");
				// System.out.println(labelIndex + " "
				// + StringEscapeUtils.unescapeJson(query));
				// TweetSelectExtractor extractor = new TweetSelectExtractor(
				// query);
				// System.out.println(labelIndex + " " + extractor);
				//
				// labelIndex++;
				// }

				if (StringUtils.contains(query, "insert")
						&& StringUtils.contains(query, "tweet")) {
					System.out.println("In extractor");
					System.out.println(labelIndex + " "
							+ StringEscapeUtils.unescapeJson(query));
					TweetInsertExtractor extractor = new TweetInsertExtractor(
							query);
					System.out.println(labelIndex + " " + extractor);

					labelIndex++;
				}

				// TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

			}
			source.closeStream();

		} catch (InterruptedException | JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
