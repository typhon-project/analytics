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
import commons.Event;
import commons.EventSchema;
import commons.PostEvent;
import utilities.Utilities;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.Statement.Insert;

public class AnalyticsScenarioOne {

	final static double DISTANCE_THRESHOLD = 5000.0;
	final static int COUNT_THRESHOLD = 2;
	final static int WINDOW_LENGTH = 60;
	final static int WINDOW_SLIDE = 20;

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
								espObj.setVIN(Integer.parseInt(kv.getValue().yieldTree()));
							} else if (kv.getKey().getString().equalsIgnoreCase("timestamp")) {
								espObj.setTimestamp(kv.getValue().yieldTree());
							} else if (kv.getKey().getString().equalsIgnoreCase("vehicle_position")) {
								espObj.setPosition(kv.getValue().yieldTree());
							}
						}
						System.out.println(espObj);
						return espObj;
					}

				})
				.timeWindowAll(Time.seconds(WINDOW_LENGTH), Time.seconds(WINDOW_SLIDE))
				.apply(new AllWindowFunction<ESP, ESP, TimeWindow>() {

					@Override
					public void apply(TimeWindow timeWindow, Iterable<ESP> allEventsInWindow, Collector<ESP> result) throws Exception {
						for (ESP espObj : allEventsInWindow) {
							double la1 = Double.parseDouble(espObj.getPosition().split(" ")[0].substring(3, espObj.getPosition().split(" ")[0].length()));
							double lo1 = Double.parseDouble(espObj.getPosition().split(" ")[1].substring(3, espObj.getPosition().split(" ")[1].length()-1));
							int count = 0;
							for (ESP nestedESPObj : allEventsInWindow) {
								double la2 = Double.parseDouble(nestedESPObj.getPosition().split(" ")[0].substring(3, nestedESPObj.getPosition().split(" ")[0].length()));
								double lo2 = Double.parseDouble(nestedESPObj.getPosition().split(" ")[1].substring(3, nestedESPObj.getPosition().split(" ")[1].length()-1));
								double distance = Utilities.distance(la1, la2, lo1, lo2, 0.0, 0.0);
								if(distance != 0.0 && distance <= DISTANCE_THRESHOLD) {
									count++;
								}
							}
							if (count >= COUNT_THRESHOLD) {
								System.out.println("Problem in area " + la1 + " " + lo1 + "(" + count + " events in " + WINDOW_LENGTH + " seconds)");
							}
							System.out.println("=========");
						}
						
					}
				});
		
		DataStream<Event> vehicleMetaDataEvents = PostEventStream
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
				});

		env.execute();

	}

}
