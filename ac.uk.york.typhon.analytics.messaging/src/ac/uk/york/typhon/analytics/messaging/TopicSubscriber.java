package ac.uk.york.typhon.analytics.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.camel.language.Constant;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import ac.uk.york.typhon.analytics.commons.AppConfiguration;
import ac.uk.york.typhon.analytics.commons.Constants;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.commons.enums.ITopicType;
import ac.uk.york.typhon.analytics.commons.serialization.EventSchema;

public class TopicSubscriber {

//	private static Map<ITopicType, Class<?>> topicEventMap;
	private static Map<ITopicType, FlinkKafkaConsumer09<Event>> topicStreamConsumerMap;

	static {
		initialize();
	}

	/**
	 * 
	 */
	private static void initialize() {
		topicStreamConsumerMap = new HashMap<ITopicType, FlinkKafkaConsumer09<Event>>();

//		topicEventMap = new HashMap<ITopicType, Class<?>>();
//		topicEventMap.put(AnalyticTopicType.PRE, PreEvent.class);
//		System.out.println("-------------- Watch OUT I don't have access to the Authorization Topic Type - Topic Subscriber");
////		topicEventMap.put(ExternalTopicType.AUTHORIZATION, PreEvent.class);
//		topicEventMap.put(AnalyticTopicType.POST, PostEvent.class);
		
		
//		topicEventMap = new HashMap<Constants.TopicName, Class<?>>();
//		topicEventMap.put(Constants.TopicName.PRE, PreEvent.class);
//		topicEventMap.put(Constants.TopicName.AUTHORIZATION, PreEvent.class);
//		topicEventMap.put(Constants.TopicName.POST, PostEvent.class);
	}

	/**
	 * 
	 * @param topic
	 * @return
	 */
	public static FlinkKafkaConsumer09<Event> retrieveStreamConsumer(
			ITopicType topic, Class<?> eventClass) {
		FlinkKafkaConsumer09<Event> topicConsumer = null;

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

//			topicConsumer = new FlinkKafkaConsumer09<Event>(topic.getLabel(),
//					new EventSchema(topicEventMap.get(topic)), properties);
			
			topicConsumer = new FlinkKafkaConsumer09<Event>(topic.getLabel(),
					new EventSchema(eventClass), properties);

			topicStreamConsumerMap.put(topic, topicConsumer);

		}
		return topicConsumer;
	}

}
