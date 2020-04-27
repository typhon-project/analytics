import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.examples.vw.datatypes.ESP;
import ac.york.typhon.analytics.examples.vw.datatypes.VehicleMetadata;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.Statement.Insert;
import engineering.swat.typhonql.ast.TyphonQLASTParser;
import enrichment.EnrichmentFunction;

public class AnalyticsScenarioTwo implements IAnalyzer {

	@Override
	public void analyze(
			DataStream<Event> eventsStream) throws Exception {
		DataStream<ESP> espEvents = eventsStream.filter(new FilterFunction<Event>() {

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
		}).filter(new FilterFunction<Event>() {

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
		}).map(new MapFunction<Event, ESP>() {

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
						espObj.setVehicle_position(kv.getValue().yieldTree());
					}
				}
//						System.out.println(espObj);
				return espObj;
			}

		}).keyBy("VIN");

		DataStream<VehicleMetadata> vehicleMetaDataEvents = eventsStream.filter(new FilterFunction<Event>() {

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
		}).filter(new FilterFunction<Event>() {

			@Override
			public boolean filter(Event event) throws Exception {
				PostEvent postEvent = (PostEvent) event;
				String query = postEvent.getQuery();
				Request request = TyphonQLASTParser.parseTyphonQLRequest((query).toCharArray());
				if (request.hasStm()) {
					return (request.getStm().getObjs().get(0).getEntity().getString()
							.equalsIgnoreCase("VehicleMetadata"));
				}
				return false;
			}
		}).map(new MapFunction<Event, VehicleMetadata>() {

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
//						System.out.println(metadataObj);
				return metadataObj;
			}
		}).keyBy("VIN");

		DataStream<Tuple2<ESP, VehicleMetadata>> enrichedEvents = espEvents.connect(vehicleMetaDataEvents)
				.flatMap(new EnrichmentFunction());

		enrichedEvents.print();
	}

}
