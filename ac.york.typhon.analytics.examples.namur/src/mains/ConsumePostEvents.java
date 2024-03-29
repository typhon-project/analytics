package mains;

import java.util.Properties;
import java.util.UUID;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.serialization.EventSchema;

public class ConsumePostEvents {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.1.18:29092");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty("auto.offset.reset", "earliest");

		DataStream<Event> PostEventStream = env.addSource(
				new FlinkKafkaConsumer<Event>("POST", new EventSchema(PostEvent.class), properties));
	
		// This is where you need to write your code in Flink - which is actual Java with some extra
		// operators that allow you to deal with streams of data. If you are not familiar with Flink
		// I have a simple map function below. Just type your java code in there. If you don't want
		// to work with Flink, then search online on other ways to consumer Kafka queues (Kafka itself has
		// connectors that allow you consume queues). Flink though offers all the infrastructure for
		// automatic distribution of the tasks. But selecting the technology that you will us to write
		// your evolution tasks is up to you. 
		PostEventStream.map(new MapFunction<Event, String>() {

			@Override
			public String map(Event event) throws Exception {
				PostEvent postEvent = (PostEvent) event;
				System.out.println("A new post event arrived at the POST queue and I consumed it. Here it is:");
				System.out.println(postEvent);
				return "";
			}
			
		});
		
		env.execute();

	}

}
