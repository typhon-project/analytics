package analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.time.Time;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import analytics.utils.PostEventTimeAssigner;

public class TopProducts implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {
		
		eventsStream
		.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		})
		.filter(new FilterFunction<PostEvent>(
				) {
			
			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("insert OrderedProduct");
			}
		})
		.assignTimestampsAndWatermarks(new PostEventTimeAssigner())
		.map(new MapFunction<PostEvent, Tuple3<Integer, String, Date>>() {

			@Override
			public Tuple3<Integer, String, Date> map(PostEvent arg0) throws Exception {
				String productId = arg0.getQuery().split("product:")[1];
				Tuple3 tuple = new Tuple3<Integer, String, Date>(1, productId, arg0.getStartTime());
				return tuple;
//				String orderedProductId = arg0.getQuery().split("@id == ")[1];
//				return orderedProductId;
			}
		})
		.keyBy(1)
		.timeWindow(Time.seconds(5))
		.sum(0)
		.print();
		
		
		return eventsStream;
	}

}
