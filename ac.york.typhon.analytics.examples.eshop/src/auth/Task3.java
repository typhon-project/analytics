package auth;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTaskAsStream;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class Task3 extends GenericAuthorisationTaskAsStream {

	@Override
	public boolean checkCondition(Event event) {
		// protected region Task3CheckConditionId on begin
		return ((PreEvent) event).getQuery().contains("insert Category");
		// protected region Task3CheckConditionId end
	}

	@Override
	public DataStream<Event> shouldIRejectAsStream(DataStream<Event> events) {
		
		// protected region Task3ShouldIRejectId on begin
		DataStream checkedEvents = events.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event value) throws Exception {
				if (((PreEvent) value).getQuery().contains("Clothing")) {
					((PreEvent) value).setAuthenticated(false);
				}
				return value;
			}
			
		});
		// protected region Task3ShouldIRejectId end
		return checkedEvents;
	}
}