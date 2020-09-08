package analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.Product;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import analytics.utilities.BoundedOutOfOrdernessGenerator;

public class TopBrowsedProduct implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		eventsStream
		.map(new MapFunction<Event, DeserializedPostEvent>() {

			@Override
			public DeserializedPostEvent map(Event arg0) throws Exception {
				return (DeserializedPostEvent) arg0;
			}
		})
		.filter(new FilterFunction<DeserializedPostEvent>(
				) {
			
			@Override
			public boolean filter(DeserializedPostEvent pe) throws Exception {
				return pe.getQuery().contains("from Product p select p ");
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGenerator())
		.map(new MapFunction<DeserializedPostEvent, Tuple2<String, Integer>>() {

			@Override
			public Tuple2<String, Integer> map(DeserializedPostEvent value) throws Exception {
				String id = value.getQuery().split("p.@id == #")[1];
				Tuple2<String, Integer> tuple = new Tuple2<String, Integer>(id, 1);
				return tuple;
			}
		})
		.keyBy(0)
		.timeWindow(Time.seconds(15))
		.sum(1)
		.map(new MapFunction<Tuple2<String,Integer>, Tuple3<String,Integer, String>>() {

			@Override
			public Tuple3<String, Integer, String> map(Tuple2<String, Integer> value) throws Exception {
				long timestamp = System.currentTimeMillis();
				Date dateNow = new Date(timestamp);
				String timeString = dateNow.toString();
				Tuple3<String, Integer, String> tuple = new Tuple3<String, Integer, String>(value.f0, value.f1, timeString);
				return tuple;
			}
		})
		.print();
	}

}