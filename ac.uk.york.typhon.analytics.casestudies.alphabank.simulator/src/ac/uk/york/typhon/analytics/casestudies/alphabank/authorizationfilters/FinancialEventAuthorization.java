package ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationfilters;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.authorization.EventAuthorizationTask;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class FinancialEventAuthorization extends EventAuthorizationTask {

	@Override
	public boolean checkCondition(Event preEvent) {
		if (preEvent.getQuery().toLowerCase()
				.contains("insert into table fnc_ev")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DataStream<Event> analyse(DataStream<Event> splittedStream)
			throws Exception {
		
		DataStream<Event> results = splittedStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event event) throws Exception {
				((PreEvent) event).setAuthenticated(true);
				return event;
			}
		}).returns(Event.class);
		return results;
	}

}
