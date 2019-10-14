package ac.york.typhon.generator.generators.impl;

import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.TopicPublisher;
import ac.york.typhon.generator.generators.IGenerator;
import ac.york.typhon.generator.helper.GeneratorConstants;
import ac.york.typhon.generator.helper.GeneratorConstants.ResourceHeader;
import ac.york.typhon.generator.helper.Utils;
import ac.york.typhon.generator.source.IFileSource;
import ac.york.typhon.generator.source.impl.LocalFileSouceImpl;

public class TwickyGeneratorImpl implements IGenerator {

	private static String user;
	private static String host;
	private static String password;
	private static int port;
	private static String filePath;

	static {
//		loadRemoteConfiguration();
		loadLocalConfiguration();
	}

	private static void loadRemoteConfiguration() {
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

	private static void loadLocalConfiguration() {
		filePath = AppConfiguration
				.getString(GeneratorConstants.LocalResourceCredentials.FILE_PATH);

	}

	public static void main(String[] args) {
		TwickyGeneratorImpl generator = new TwickyGeneratorImpl();
		generator.generate();
	}

	@Override
	public void generate() {

		try {
//			IFileSource source = new RemoteFileSourceImpl(
//					TwickyGeneratorImpl.host, TwickyGeneratorImpl.port,
//					TwickyGeneratorImpl.user, TwickyGeneratorImpl.password,
//					TwickyGeneratorImpl.filePath);

			IFileSource source = new LocalFileSouceImpl(TwickyGeneratorImpl.filePath);

			CSVFormat csvFormat = CSVFormat.ORACLE.withHeader(
					ResourceHeader.EVENT_TIME,
					ResourceHeader.USER_HOST,
					ResourceHeader.THREAD_ID,
					ResourceHeader.SERVER_ID,
					ResourceHeader.COMMAND_TYPE,
					ResourceHeader.ARGUMENT);

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
				String query = record.get(ResourceHeader.ARGUMENT);
				Timestamp timestamp = Timestamp.valueOf(record
						.get(ResourceHeader.EVENT_TIME));
				// Timestamp timestamp = Utils.generateTimeStamp();
				String user = record.get(ResourceHeader.USER_HOST);
				Event preEvent = new PreEvent(id, query, user, timestamp,
						"dbUser");

//				 System.out.println(preEvent);
				/*************************************************/
				/*********** Comment Sleep if NOT NEEDED *********/
				Thread.sleep(RandomUtils.nextLong(0, 1000));
				/*************************************************/
				/*************************************************/
				// System.out.println(query);
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

				// if (StringUtils.contains(query, "insert")
				// && StringUtils.contains(query, "tweet")) {
				// System.out.println("In extractor");
				// System.out.println(labelIndex + " "
				// + StringEscapeUtils.unescapeJson(query));
				// TweetInsertExtractor extractor = new TweetInsertExtractor(
				// query);
				// System.out.println(labelIndex + " " + extractor);
				//
				// labelIndex++;
				// }

				TopicPublisher.publish(AnalyticTopicType.PRE, preEvent);

			}
			source.closeStream();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
