package ac.uk.york.typhon.analytics.authorization;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public class ExampleEventAuthTask1 extends EventAuthorizationTask {

	@Override
	public boolean checkCondition(Event preEvent) {
		if (preEvent.getQuery().toLowerCase().contains("select")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DataStream<Event> analyse(DataStream<Event> splittedStream) throws Exception {
		DataStream<Event> results = splittedStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event arg0) throws Exception {
				return arg0;
			}
		});
		return results;
	}

}
