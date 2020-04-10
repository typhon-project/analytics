package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class TestScenario implements IAnalyzer {

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
				return pe.getQuery().contains("insert Order {");
			}
		}).print();
		return eventsStream;
	}

}
