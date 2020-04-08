package ac.york.typhon.analytics.streaming;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.Constants;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.enums.ITopicType;
import ac.york.typhon.analytics.commons.serialization.EventSchema;

public class TopicSubscriber {

	// private static Map<ITopicType, Class<?>> topicEventMap;
	private static Map<String, FlinkKafkaConsumer<Event>> topicStreamConsumerMap;

	static {
		initialize();
	}

	/**
	 * 
	 */
	private static void initialize() {
		topicStreamConsumerMap = new HashMap<String, FlinkKafkaConsumer<Event>>();

		// topicEventMap = new HashMap<ITopicType, Class<?>>();
		// topicEventMap.put(AnalyticTopicType.PRE, PreEvent.class);
		// System.out.println("-------------- Watch OUT I don't have access to the Authorization Topic Type - Topic Subscriber");
		// // topicEventMap.put(ExternalTopicType.AUTHORIZATION,
		// PreEvent.class);
		// topicEventMap.put(AnalyticTopicType.POST, PostEvent.class);

		// topicEventMap = new HashMap<Constants.TopicName, Class<?>>();
		// topicEventMap.put(Constants.TopicName.PRE, PreEvent.class);
		// topicEventMap.put(Constants.TopicName.AUTHORIZATION, PreEvent.class);
		// topicEventMap.put(Constants.TopicName.POST, PostEvent.class);
	}

	/**
	 * 
	 * @param topic
	 * @return
	 */
	public static FlinkKafkaConsumer<Event> retrieveStreamConsumer(
			ITopicType topic, Class<?> eventClass) {
		FlinkKafkaConsumer<Event> topicConsumer = null;

		if (topicStreamConsumerMap.containsKey(topic) == true) {
			topicConsumer = topicStreamConsumerMap.get(topic);
		} else if (topicStreamConsumerMap.containsKey(topic) == false) {

			Properties properties = new Properties();

			// Broker default host:port
			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).BOOTSTRAP_SERVERS));
			// Consumer group ID
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).GROUP_ID));

			// Always read topic from start
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).AUTO_OFFSET_RESET));

			// topicConsumer = new FlinkKafkaConsumer09<Event>(topic.getLabel(),
			// new EventSchema(topicEventMap.get(topic)), properties);
			topicConsumer = new FlinkKafkaConsumer<Event>(topic.getLabel().toUpperCase(),
					new EventSchema(eventClass), properties);

			topicStreamConsumerMap.put(topic.getLabel(), topicConsumer);

		}
		return topicConsumer;
	}
	
	public static FlinkKafkaConsumer<Event> retrieveStreamConsumer(
			ITopicType topic, Class<?> eventClass, String groupId) {
		FlinkKafkaConsumer<Event> topicConsumer = null;

		if (topicStreamConsumerMap.containsKey(topic + "." + groupId) == true) {
			topicConsumer = topicStreamConsumerMap.get(topic);
		} else if (topicStreamConsumerMap.containsKey(topic + "." + groupId) == false) {

			Properties properties = new Properties();

			// Broker default host:port
			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).BOOTSTRAP_SERVERS));
			// Consumer group ID
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);

			// Always read topic from start
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).AUTO_OFFSET_RESET));

			// topicConsumer = new FlinkKafkaConsumer09<Event>(topic.getLabel(),
			// new EventSchema(topicEventMap.get(topic)), properties);
			topicConsumer = new FlinkKafkaConsumer<Event>(topic.getLabel().toUpperCase(),
					new EventSchema(eventClass), properties);
			topicStreamConsumerMap.put(topic + "." + groupId, topicConsumer);

		}
		return topicConsumer;
	}

}
