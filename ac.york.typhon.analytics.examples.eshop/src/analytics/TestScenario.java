package analytics;

import java.util.Properties;
import java.util.UUID;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import commons.Event;
import commons.EventSchema;
import commons.PostEvent;

public class TestScenario {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.1.16:29092");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty("auto.offset.reset", "earliest");

		DataStream<Event> PostEventStream = env
				.addSource(new FlinkKafkaConsumer<Event>("POST", new EventSchema(PostEvent.class), properties));

		PostEventStream.print();
		
		env.execute();

	}

}
