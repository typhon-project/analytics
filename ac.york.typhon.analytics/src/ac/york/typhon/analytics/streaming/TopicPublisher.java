package ac.york.typhon.analytics.streaming;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer.Semantic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.Constants;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.enums.ITopicType;
import ac.york.typhon.analytics.commons.serialization.EventSchema;

public class TopicPublisher {

	private static Map<ITopicType, KafkaProducer<String, Event>> topicProducerMap;
	private static Map<ITopicType, FlinkKafkaProducer<Event>> topicStreamProducerMap;

	static {
		initialize();
	}

	private static void initialize() {

		topicProducerMap = new HashMap<ITopicType, KafkaProducer<String, Event>>();
		topicStreamProducerMap = new HashMap<ITopicType, FlinkKafkaProducer<Event>>();

	}

	/**
	 * 
	 * @param topic
	 * @return
	 */
	private static KafkaProducer<String, Event> retrieveProducer(
			ITopicType topic) {
		KafkaProducer<String, Event> topicProducer = null;
		if (topicProducerMap.containsKey(topic) == true) {
			topicProducer = topicProducerMap.get(topic);
		} else if (topicProducerMap.containsKey(topic) == false) {

			Properties properties = new Properties();

			properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).BOOTSTRAP_SERVERS));

			properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
					EventSchema.class);
			properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
					EventSchema.class);

			topicProducer = new KafkaProducer<String, Event>(properties);
			topicProducerMap.put(topic, topicProducer);
		}

		return topicProducer;
	}

	/**
	 * 
	 * @param topic
	 * @return
	 */
	public static FlinkKafkaProducer<Event> retrieveStreamProducer(
			ITopicType topic) {
		FlinkKafkaProducer<Event> topicStreamProducer = null;

		if (topicStreamProducerMap.containsKey(topic) == true) {
			topicStreamProducer = topicStreamProducerMap.get(topic);
		} else if (topicStreamProducerMap.containsKey(topic) == false) {
			Properties producerConfig = new Properties();
			producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
					AppConfiguration.getString(Constants.Properties.Topic
							.name(topic.getLabel()).BOOTSTRAP_SERVERS));
			topicStreamProducer = new FlinkKafkaProducer<Event>(
					topic.getLabel(), new EventSchema(), producerConfig, Semantic.NONE);
			topicStreamProducerMap.put(topic, topicStreamProducer);
		}

		return topicStreamProducer;
	}

	/**
	 * publish the message to the destined topic
	 * 
	 * @param topic
	 * @param event
	 */
	public static void publish(ITopicType topic, Event event) {

		ProducerRecord producerRecord = new ProducerRecord(topic.getLabel(),
				event);
		retrieveProducer(topic).send(producerRecord);
	}

}
