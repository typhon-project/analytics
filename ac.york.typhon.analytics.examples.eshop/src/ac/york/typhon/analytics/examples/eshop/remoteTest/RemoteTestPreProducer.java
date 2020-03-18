package ac.york.typhon.analytics.examples.eshop.remoteTest;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.serialization.EventSchema;

public class RemoteTestPreProducer {

	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		String path = "files/input.txt";
//		DataStream<String> stream = env.readTextFile(path);
		String kafkaConnection = "10.240.55.24:29092";
		QueueProducer qp = new QueueProducer(kafkaConnection);
		PreEvent pe = new PreEvent();
		
		qp.produce("testPE", pe);
		
//		DataStream<Event> eventStream = stream.map(new MapFunction<String, Event>() {
//
//			@Override
//			public Event map(String arg0) throws Exception {
//				return new PreEvent();
//			}
//			
//			
//		});
//
//		FlinkKafkaProducer<Event> myProducer = new FlinkKafkaProducer<Event>(
//		        "localhost:29092",            // broker list
//		        "test",                  // target topic
//		        new EventSchema());   // serialization schema
//
//		eventStream.addSink(myProducer);
//		env.execute();

	}

}
