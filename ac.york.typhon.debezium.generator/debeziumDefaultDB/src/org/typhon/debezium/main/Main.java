package org.typhon.debezium.main;

import java.util.Properties;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.typhon.debezium.deserializers.ReviewDeserializer;
import org.typhon.debezium.entities.ReviewDML;
import org.typhon.debezium.deserializers.ProductDeserializer;
import org.typhon.debezium.entities.ProductDML;

public class Main {	
	
	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		Properties properties = new Properties();
		properties.setProperty("zookeeper.connect", "localhost:2181");
		properties.setProperty("bootstrap.servers", "192.168.202.197:9092");
		properties.setProperty("group.id", "test");
		properties.setProperty("auto.offset.reset", "earliest");
		
		DataStream<String> ReviewStream = env.addSource(new FlinkKafkaConsumer<String>("dbserver1.INSERT_DB_NAME_HERE.Review", new SimpleStringSchema(), properties));
		DataStream<ReviewDML> ReviewDMLStream = 
			ReviewStream.map(new MapFunction<String, ReviewDML>() {

				@Override
				public ReviewDML map(String value) throws Exception {
					ReviewDeserializer reviewDeserializer = new ReviewDeserializer();
					ReviewDML reviewDML = new ReviewDML();
					return reviewDeserializer.deserialize(value);
				}
			});
		DataStream<String> ProductStream = env.addSource(new FlinkKafkaConsumer<String>("dbserver1.INSERT_DB_NAME_HERE.Product", new SimpleStringSchema(), properties));
		DataStream<ProductDML> ProductDMLStream = 
			ProductStream.map(new MapFunction<String, ProductDML>() {

				@Override
				public ProductDML map(String value) throws Exception {
					ProductDeserializer productDeserializer = new ProductDeserializer();
					ProductDML productDML = new ProductDML();
					return productDeserializer.deserialize(value);
				}
			});
		
		
		env.execute();
	}
}
