import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import ac.york.typhon.analytics.examples.vw.datatypes.ESP;
import ac.york.typhon.analytics.examples.vw.datatypes.VehicleMetadata;
import commons.Event;
import commons.EventSchema;
import commons.PostEvent;
import utilities.Utilities;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.Statement.Insert;
import io.usethesource.capsule.Map;

public class AnalyticsScenarioTwo {

	public static void main(String[] args) throws Exception {
		
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.1.16:29092");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty("auto.offset.reset", "earliest");

		DataStream<Event> PostEventStream = env
				.addSource(new FlinkKafkaConsumer<Event>("POST", new EventSchema(PostEvent.class), properties));

		DataStream<ESP> espEvents = PostEventStream
				.filter(new FilterFunction<Event>() {

					@Override
					public boolean filter(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						if (request.hasStm()) {
							return request.getStm() instanceof Insert;
						}
						return false;
					}
				})
				.filter(new FilterFunction<Event>() {

					@Override
					public boolean filter(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						if (request.hasStm()) {
							 return (request.getStm().getObjs().get(0).getEntity().getString().equalsIgnoreCase("ESP"));
						}
						return false;
					}
				})
				.map(new MapFunction<Event, ESP>() {

					@Override
					public ESP map(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						ArrayList<KeyVal> keyValues = (ArrayList<KeyVal>) request.getStm().getObjs().get(0).getKeyVals();
						ESP espObj = new ESP();
						for (KeyVal kv : keyValues) {
							if (kv.getKey().getString().equalsIgnoreCase("VIN")) {
								espObj.setVIN(Long.parseLong(kv.getValue().yieldTree()));
							} else if (kv.getKey().getString().equalsIgnoreCase("timestamp")) {
								espObj.setTimestamp(kv.getValue().yieldTree());
							} else if (kv.getKey().getString().equalsIgnoreCase("vehicle_position")) {
								espObj.setPosition(kv.getValue().yieldTree());
							}
						}
						System.out.println(espObj);
						return espObj;
					}

				});
		
		DataStream<VehicleMetadata> vehicleMetaDataEvents = PostEventStream
				.filter(new FilterFunction<Event>() {

					@Override
					public boolean filter(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						if (request.hasStm()) {
							return request.getStm() instanceof Insert;
						}
						return false;
					}
				})
				.filter(new FilterFunction<Event>() {

					@Override
					public boolean filter(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						if (request.hasStm()) {
							 return (request.getStm().getObjs().get(0).getEntity().getString().equalsIgnoreCase("VehicleMetadata"));
						}
						return false;
					}
				})
				.map(new MapFunction<Event, VehicleMetadata>() {

					@Override
					public VehicleMetadata map(Event event) throws Exception {
						PostEvent postEvent = (PostEvent) event;
						String query = postEvent.getQuery();
						Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
						ArrayList<KeyVal> keyValues = (ArrayList<KeyVal>) request.getStm().getObjs().get(0).getKeyVals();
						VehicleMetadata metadataObj = new VehicleMetadata();
						for (KeyVal kv : keyValues) {
							if (kv.getKey().getString().equalsIgnoreCase("VIN")) {
								metadataObj.setVIN(Long.parseLong(kv.getValue().yieldTree()));
							} else if (kv.getKey().getString().equalsIgnoreCase("brand")) {
								metadataObj.setBrand(kv.getValue().yieldTree());
							} else if (kv.getKey().getString().equalsIgnoreCase("model")) {
								metadataObj.setModel(kv.getValue().yieldTree());
							} else if (kv.getKey().getString().equalsIgnoreCase("constr_year")) {
								metadataObj.setConstr_year(Integer.parseInt(kv.getValue().yieldTree()));
							} else if (kv.getKey().getString().equalsIgnoreCase("color")) {
								metadataObj.setColor(kv.getValue().yieldTree());
							} else if (kv.getKey().getString().equalsIgnoreCase("t_sensor_h")) {
								metadataObj.setT_sensor_h(Integer.parseInt(kv.getValue().yieldTree()));
							} else if (kv.getKey().getString().equalsIgnoreCase("engine_type")) {
								metadataObj.setEngine_type(kv.getValue().yieldTree());
							}
						}
						System.out.println(metadataObj);
						return metadataObj;
					}
				});

		env.execute();

	}

}
