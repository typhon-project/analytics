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

public class TopReviewers implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		
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
				return pe.getQuery().contains("insert Review");
			}
		})
		.assignTimestampsAndWatermarks(new PostEventTimeAssigner())
		.map(new MapFunction<PostEvent, Tuple3<Integer, String, Date>>() {

			@Override
			public Tuple3<Integer, String, Date> map(PostEvent arg0) throws Exception {
				String userId = arg0.getQuery().split("user:")[1];
				Tuple3<Integer, String, Date> tuple = new Tuple3<Integer, String, Date>(1, userId, arg0.getStartTime());
				return tuple;
			}
		})
		.keyBy(1)
		.timeWindow(Time.seconds(10))
		.sum(0)
		.print();
	}

}
