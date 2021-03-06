import java.util.ArrayList;
import java.util.Date;
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

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.ESP;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import utilities.Utilities;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import engineering.swat.typhonql.ast.Statement.Insert;

public class AnalyticsScenarioOneDes implements IAnalyzer {

	final static double DISTANCE_THRESHOLD = 5000.0;
	final static int COUNT_THRESHOLD = 2;
	final static int WINDOW_LENGTH = 6;
	final static int WINDOW_SLIDE = 2;

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		DataStream<ESP> espEvents = eventsStream.filter(new FilterFunction<Event>() {

			@Override
			public boolean filter(Event event) throws Exception {
				System.out.println(event);
				PostEvent postEvent = (PostEvent) event;
				String query = postEvent.getPreEvent().getQuery();
				Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
				System.out.println("Hi 1");
				if (request.hasStm()) {
					return request.getStm() instanceof Insert;
				}
				return false;
			}
		}).filter(new FilterFunction<Event>() {

			@Override
			public boolean filter(Event event) throws Exception {
				PostEvent postEvent = (PostEvent) event;
				String query = postEvent.getPreEvent().getQuery();
				Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
				System.out.println("Hi 2");
				if (request.hasStm()) {
					return (request.getStm().getObjs().get(0).getEntity().getString().equalsIgnoreCase("ESP"));
				}
				return false;
			}
		}).map(new MapFunction<Event, ESP>() {

			@Override
			public ESP map(Event value) throws Exception {
				DeserializedPostEvent desEvent = (DeserializedPostEvent) value;
				InsertCommand command = (InsertCommand) desEvent.getCommands().get(0);
				return (ESP) command.getInsertedEntities().get(0);
			}
		})
			
//			map(new MapFunction<Event, ESP>() {
//
//			@Override
//			public ESP map(Event event) throws Exception {
//				PostEvent postEvent = (PostEvent) event;
//				String query = postEvent.getQuery();
//				Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
//				ArrayList<KeyVal> keyValues = (ArrayList<KeyVal>) request.getStm().getObjs().get(0).getKeyVals();
//				ESP espObj = new ESP();
//				for (KeyVal kv : keyValues) {
////					if (kv.getKey().getString().equalsIgnoreCase("VIN")) {
////						espObj.setVIN(Integer.parseInt(kv.getValue().yieldTree()));
////					} else 
//					if (kv.getKey().getString().equalsIgnoreCase("timeStamp")) {
//						espObj.setTimestamp(kv.getValue().yieldTree());
//					} else if (kv.getKey().getString().equalsIgnoreCase("vehicle_position")) {
//						GeoPoint gp = new GeoPoint();
//						System.out.println(kv.getValue().yieldTree());
////						espObj.setVehicle_position(kv.getValue().yieldTree());
//					}
//				}
//				System.out.println(espObj);
//				return espObj;
//			}
//
//		});
				
				.timeWindowAll(Time.seconds(WINDOW_LENGTH), Time.seconds(WINDOW_SLIDE))
				.apply(new AllWindowFunction<ESP, ESP, TimeWindow>() {

					@Override
					public void apply(TimeWindow timeWindow, Iterable<ESP> allEventsInWindow, Collector<ESP> result)
							throws Exception {
						for (ESP espObj : allEventsInWindow) {
							double la1 = espObj.getVehicle_position().getX();
							double lo1 = espObj.getVehicle_position().getY();
							int count = 0;
							System.out.println(la1 + " " + lo1);
							for (ESP nestedESPObj : allEventsInWindow) {
								double la2 = nestedESPObj.getVehicle_position().getX();
								double lo2 = nestedESPObj.getVehicle_position().getY();
								
								
//								double la2 = Double.parseDouble(nestedESPObj.getVehicle_position().split(" ")[0]
//										.substring(0, nestedESPObj.getVehicle_position().split(" ")[0].length()));
//								double lo2 = Double.parseDouble(nestedESPObj.getVehicle_position().split(" ")[1]
//										.substring(0, nestedESPObj.getVehicle_position().split(" ")[1].length() - 1));
								double distance = Utilities.distance(la1, la2, lo1, lo2, 0.0, 0.0);
								if (distance != 0.0 && distance <= DISTANCE_THRESHOLD) {
									count++;
								}
							}
							if (count >= COUNT_THRESHOLD) {
								System.out.println("Problem in area " + la1 + " " + lo1 + "(" + count + " events in "
										+ WINDOW_LENGTH + " seconds)");
							}
							System.out.println("=========");
						}

					}
				});
	}

}
