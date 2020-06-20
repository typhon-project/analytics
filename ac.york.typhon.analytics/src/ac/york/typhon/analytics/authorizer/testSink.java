package ac.york.typhon.analytics.authorizer;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer.Semantic;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerConfig;

import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.Constants;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.commons.serialization.EventSchema;
import ac.york.typhon.analytics.streaming.StreamManager;

public class testSink {
	
	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
	
		Properties props = new Properties();
	
	
		props.put("bootstrap.servers", "localhost:29092");
	
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				EventSchema.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				EventSchema.class);
		FlinkKafkaProducer<Event> myProducer = new FlinkKafkaProducer<Event>("AUTH",
				new EventSchema(),
				props,
				Semantic.AT_LEAST_ONCE);   // serialization schema

		dataStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event value) throws Exception {
				// TODO Auto-generated method stub
				return value;
			}
		});
		dataStream.print();
		dataStream.addSink(myProducer);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
	}

}
