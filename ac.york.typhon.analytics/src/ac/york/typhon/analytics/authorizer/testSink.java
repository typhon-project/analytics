package ac.york.typhon.analytics.authorizer;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer.Semantic;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.commons.serialization.EventSchema;
import ac.york.typhon.analytics.streaming.StreamManager;
import akka.stream.impl.fusing.Map;
import scala.util.Random;

public class testSink {

	public static void main(String[] args) throws Exception {

//		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.POST, PostEvent.class, UUID.randomUUID().toString());
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty("auto.offset.reset", "earliest");
		DataStream<Event> dataStream = env
			.addSource(new FlinkKafkaConsumer<Event>("POST", new EventSchema(PostEvent.class), properties));
		env.setParallelism(8);
		
		Properties props = new Properties();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		KafkaSerializationSchema<Event> serializationSchema = (value, timestamp) -> new ProducerRecord<byte[], byte[]>("AUTH", value.convertObjectToByteArray(value));

		FlinkKafkaProducer<Event> myProducer = new FlinkKafkaProducer<>(
				"AUTH", 
				new EventSchema(),
				props, 
				Semantic.AT_LEAST_ONCE); // serialization schema

		dataStream
		.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event value) throws Exception {
				Random r = new Random();
				
				Thread.sleep(r.nextInt(3000));
				return value;
			}
		})
		.map(new MapFunction<Event, Tuple2<String, Event>>() {

			@Override
			public Tuple2<String, Event> map(Event value) throws Exception {
				
				return new Tuple2<String, Event>(value.getQuery(), value);
			}
			
		})
		
		.keyBy(0)
		.map(new MapFunction<Tuple2<String, Event>, Event>() {

			@Override
			public Event map(Tuple2<String, Event> value) throws Exception {
				Random r = new Random();
				
				Thread.sleep(r.nextInt(3000));
				return value.f1;
			}
		})
		.print();
		myProducer.setWriteTimestampToKafka(true);
		dataStream.addSink(myProducer);
		env.execute();
//		StreamManager.initializeSink(AnalyticTopicType.AUTH, dataStream);
//		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);
		
//StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//		
//		Properties properties = new Properties();
//		properties.setProperty("bootstrap.servers", "localhost:9092");
//		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
//		properties.setProperty("auto.offset.reset", "earliest");
//		
//		DataStream<String> dataStream = env
//			.addSource(new FlinkKafkaConsumer<String>("POST2", new SimpleStringSchema(), properties));
//		
//		
//		Properties props = new Properties();
//		KafkaSerializationSchema<String> serializationSchema = (value, timestamp) -> new ProducerRecord<byte[], byte[]>("AUTH", value.getBytes());
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//
//		FlinkKafkaProducer<String> myProducer = new FlinkKafkaProducer<String>(
//				"AUTH", 
//				serializationSchema ,
//				props, 
//				Semantic.AT_LEAST_ONCE); // serialization schema
//
//		dataStream.map(new MapFunction<String, String>() {
//
//			@Override
//			public String map(String value) throws Exception {
//				System.out.println(value);
//				return value;
//			}
//		});
//		myProducer.setWriteTimestampToKafka(true);
//		dataStream.addSink(myProducer);
//		env.execute();

	}

}
