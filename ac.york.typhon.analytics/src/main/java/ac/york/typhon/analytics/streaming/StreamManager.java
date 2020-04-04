package ac.york.typhon.analytics.streaming;

import java.util.HashMap;
import java.util.Map;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.enums.ITopicType;

public class StreamManager {

	private static Map<ITopicType, StreamExecutionEnvironment> streamExecutionEnvironmentMap;

	static {
		initialize();
	}

	private static void initialize() {

		streamExecutionEnvironmentMap = new HashMap<ITopicType, StreamExecutionEnvironment>();
	}

	/**
	 * Initialize the stream source from a topic
	 * 
	 * @param topic
	 * @return
	 */
	public static DataStream<Event> initializeSource(ITopicType topic, Class<?> eventClass) {

		// Configuration flinkConfig = new Configuration();
		// flinkConfig.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
		// streamExecutionEnvironment = StreamExecutionEnvironment
		// .createLocalEnvironmentWithWebUI(flinkConfig);

		StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment
				.createLocalEnvironment(4);

		streamExecutionEnvironment
				.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		streamExecutionEnvironmentMap.put(topic, streamExecutionEnvironment);

		// retrieve the topic consumer that subscribed to the topic
		FlinkKafkaConsumer<Event> topicConsumer = TopicSubscriber
				.retrieveStreamConsumer(topic, eventClass);

		// assign the consumer as the data source of the stream
		DataStream<Event> dataStream = streamExecutionEnvironment
				.addSource(topicConsumer);

		return dataStream;

	}

	/**
	 * Initialize the
	 * 
	 * @param topic
	 * @param dataStream
	 * @return
	 */
	public static DataStream<Event> initializeSink(ITopicType topic,
			DataStream<Event> dataStream) {

		dataStream.addSink(TopicPublisher.retrieveStreamProducer(topic));

		return dataStream;

	}

	public static void startExecutionEnvironment(ITopicType topic)
			throws Exception {
		streamExecutionEnvironmentMap.get(topic).execute();
	}

}
