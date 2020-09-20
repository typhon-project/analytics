package auth;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTaskAsStream;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class Task1 extends GenericAuthorisationTaskAsStream {

	@Override
	public boolean checkCondition(Event event) {
		// protected region Task1CheckConditionId on begin
		return ((PreEvent) event).getQuery().contains("insert Category");
		// protected region Task1CheckConditionId end
	}

	@Override
	public DataStream<Event> shouldIRejectAsStream(DataStream<Event> events) {
		// protected region Task1ShouldIRejectId on begin
		DataStream<Event> checkedEvents = events.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event value) throws Exception {
				if (((PreEvent) value).getQuery().contains("Computer")) {
					((PreEvent) value).setAuthenticated(false);
				}
				return value;
			}
			
		});
		// protected region Task1ShouldIRejectId end
		return checkedEvents;
	}
}