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

public class TopCategories implements IAnalyzer {

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
				return pe.getQuery().contains("insert OrderedProduct") || pe.getQuery().contains("insert Product");
			}
		})
		.print();
	}

}
