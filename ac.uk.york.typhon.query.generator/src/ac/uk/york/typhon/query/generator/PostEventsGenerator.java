package ac.uk.york.typhon.query.generator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.commands.DMLCommand;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;
import ac.uk.york.typhon.query.generator.enums.QueryLanguageTopicType;
import ac.uk.york.typhon.query.generator.sqlbuilder.StatementFactory;

/*
 * This class is to simulate TyphonQL generated POST events
 */
public class PostEventsGenerator {

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

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				QueryLanguageTopicType.AUTHORIZATION, PreEvent.class);

		// dataStream.print(); //print the data stream as received

		dataStream = dataStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event event) throws Exception {
				PreEvent preEvent = (PreEvent) event;
				Event postEvent = null;
				if (preEvent.isAuthenticated()) {

					DMLCommand dmlCommand = StatementFactory
							.initializeDML(preEvent.getQuery());

					postEvent = new PostEvent(event.getId(), event.getQuery(),
							new Boolean(true), preEvent.getQueryTime(),
							PostEventsGenerator.generateTimeStamp(), preEvent);

				}

				return postEvent;
			}
		}).returns(Event.class);

		StreamManager.initializeSink(AnalyticTopicType.POST, dataStream);

		StreamManager
				.startExecutionEnvironment(QueryLanguageTopicType.AUTHORIZATION);

	}

	// public static void loadEventsFromFile(IMessageHandler messageHandler)
	// throws IOException, URISyntaxException, InterruptedException {
	// try {
	//
	// // load events from the CSV files
	// URL url = ClassLoaderUtil.getResource(GeneratorConstants.CSV.FILE_NAME,
	// PostEventsGenerator.class);
	// Path path = Paths.get(url.toURI());
	//
	// Reader reader = new FileReader(path.toString());
	// Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(
	// reader);
	//
	// String id = null;
	// Timestamp postStartTimeStamp = null;
	// Timestamp postEndTimeStamp = null;
	//
	// for (CSVRecord record : records) {
	//
	// StatementFactory.initializeDML(record);
	//
	// String query = StatementFactory.getStatmentString();
	// DMLCommand dmlCommand = StatementFactory.getDmlCommand();
	//
	// if (StringUtils.isNotBlank(query)) {
	//
	// postStartTimeStamp = generateTimeStamp();
	// id = generateRandomId();
	//
	// Thread.sleep(RandomUtils.nextLong(0, 1000));
	//
	// Event preEvent = new PreEvent(id, query, "user",
	// generateTimeStamp(), "dbUser");
	//
	// messageHandler.sendMessage(preEvent);
	//
	// Thread.sleep(RandomUtils.nextLong(0, 2000));
	//
	// postEndTimeStamp = generateTimeStamp();
	//
	// Event postEvent = new PostEvent(id, query,
	// new Boolean(true), postStartTimeStamp,
	// postEndTimeStamp, (PreEvent) preEvent, dmlCommand);
	//
	// messageHandler.sendMessage(postEvent);
	// }
	// }
	// } catch (FileNotFoundException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// }

	// public static void loadEventsFromFile(PreEventPublisher preEPub,
	// PostEventPublisher postEPub) throws Exception {
	// try {
	//
	// // load events from the CSV files
	// URL url = ClassLoaderUtil.getResource(GeneratorConstants.CSV.FILE_NAME,
	// PostEventsGenerator.class);
	// Path path = Paths.get(url.toURI());
	//
	// Reader reader = new FileReader(path.toString());
	// Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(
	// reader);
	//
	// String id = null;
	// Timestamp postStartTimeStamp = null;
	// Timestamp postEndTimeStamp = null;
	//
	// for (CSVRecord record : records) {
	//
	// StatementFactory.initializeDML(record);
	//
	// String query = StatementFactory.getStatmentString();
	// DMLCommand dmlCommand = StatementFactory.getDmlCommand();
	//
	// if (StringUtils.isNotBlank(query)) {
	//
	// postStartTimeStamp = generateTimeStamp();
	// id = generateRandomId();
	//
	// Thread.sleep(RandomUtils.nextLong(0, 1000));
	//
	// Event preEvent = new PreEvent(id, query, "user",
	// generateTimeStamp(), "dbUser");
	//
	// TopicPublisher.publish(Constants.Topic.PRE, (PreEvent) preEvent);
	//
	// // preEPub.transmitEvent((PreEvent) preEvent);
	//
	// Thread.sleep(RandomUtils.nextLong(0, 2000));
	//
	// // DataStream<Event> dataStream =
	// TopicConsumer.subscribe(Constants.Topic.PRE);
	// // dataStream.print();
	// //
	// // TopicConsumer.startReading();
	//
	//
	// // AuthResponsesQueue authRQ =
	// // AuthResponsesQueue.getInstance();
	// // AuthResponseConsumer authRCons = new
	// // AuthResponseConsumer(authRQ, "AuthResponse");
	// // DataStream<AuthResponse> dataStream =
	// // authRCons.getDataStream();
	// // // Configuration config = new Configuration();
	// // //
	// // config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,
	// // true);
	// // // StreamExecutionEnvironment executionEnvironment =
	// // StreamExecutionEnvironment
	// // // .createLocalEnvironmentWithWebUI(config);
	// // // executionEnvironment
	// // //
	// // .setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
	// // // DataStream<MessageDTO> dataStream =
	// // executionEnvironment.addSource(queueConsumer);
	// // dataStream.print();
	// // authRCons.getEnv().execute();
	// //
	// // postEndTimeStamp = generateTimeStamp();
	// //
	// // Event postEvent = new PostEvent(id, query,
	// // new Boolean(true), postStartTimeStamp,
	// // postEndTimeStamp, (PreEvent) preEvent, dmlCommand);
	// //
	// // postEPub.transmitEvent((PostEvent) postEvent);
	//
	// }
	// }
	// } catch (FileNotFoundException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// }

	// PreEventQueue preEQ = PreEventQueue.getInstance();
	// PreEventPublisher preEPub = new PreEventPublisher(preEQ, "PreEvent");
	// PostEventQueue postEQ = PostEventQueue.getInstance();
	// PostEventPublisher postEPub = new PostEventPublisher(postEQ,
	// "PostEvent");

	// PreEventQueue preEQ = PreEventQueue.getInstance();
	// PreEventPublisher preEPub = null;// new PreEventPublisher(preEQ,
	// // "PreEvent");
	// PostEventQueue postEQ = PostEventQueue.getInstance();
	// PostEventPublisher postEPub = null;// new
	// PostEventPublisher(postEQ,"PostEvent");

	// the message handler to send messages to Kafka or any queueing
	// framework
	// IMessageHandler messageHandler = new MessageHandlerImpl();

	// continuous load of events from the CSV and send them to the database
	// and the message queue.
	// while (true) {
	// // loadEventsFromFile(messageHandler);
	// PostEventsGenerator.loadEventsFromFile(preEPub, postEPub);
	// }
	// newsEventAccess.closeConnection();
	// messageHandler.closeConnection();

}
