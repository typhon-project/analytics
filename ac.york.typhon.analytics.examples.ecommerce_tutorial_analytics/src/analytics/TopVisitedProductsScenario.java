package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.time.Time;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class TopVisitedProductsScenario implements IAnalyzer {

	public void analyze(DataStream<Event> eventsStream) throws Exception {
		eventsStream
		.map(new MapFunction<Event, DeserializedPostEvent>() {

			public DeserializedPostEvent map(Event arg0) throws Exception {
				return (DeserializedPostEvent) arg0;
			}
		})
		.filter(new FilterFunction<DeserializedPostEvent>(
				) {
			
			public boolean filter(DeserializedPostEvent pe) throws Exception {
				return pe.getPreEvent().getQuery().contains("from Product p select p");
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGenerator())
		.map(new MapFunction<DeserializedPostEvent, Tuple2<String, Integer>>() {

			public Tuple2<String, Integer> map(DeserializedPostEvent value) throws Exception {
				String idFirstPart = value.getPreEvent().getQuery().split("p.@id == #")[1];
				String id = idFirstPart.substring(0, idFirstPart.length() - 2);
				Tuple2<String, Integer> tuple = new Tuple2<String, Integer>(id, 1);
				return tuple;
			}
		})
		.keyBy(0)
		.timeWindow(Time.seconds(15))
		.sum(1)
		.map(new MapFunction<Tuple2<String,Integer>, Tuple2<String,Integer>>() {

			public Tuple2<String, Integer> map(Tuple2<String, Integer> value) throws Exception {
				System.out.println("Product with id: " + value.f0 + " has been visited " + value.f1 + " times.");
				return value;
			}
		});
	}

}