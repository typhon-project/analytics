package ac.york.typhon.analytics.examples.eshop.remoteTest;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

public class RemoteTestPreConsumer {

	public static void main(String[] args) throws Exception {
		
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
//		// only required for Kafka 0.8
//		properties.setProperty("zookeeper.connect", "localhost:2181");
		properties.setProperty("group.id", "test");
		DataStream<String> stream = env.addSource(new FlinkKafkaConsumer<>("topic", new SimpleStringSchema(), properties));
		stream.map(new MapFunction<String, String>() {

			@Override
			public String map(String value) throws Exception {
				System.out.println("Value: " + value);
				return value;
			}
			
		});
		env.execute();

	}

}
