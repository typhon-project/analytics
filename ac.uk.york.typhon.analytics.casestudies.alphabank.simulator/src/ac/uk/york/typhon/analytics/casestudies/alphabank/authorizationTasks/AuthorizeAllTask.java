package ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.authorization.EventAuthorizationTask;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class AuthorizeAllTask extends EventAuthorizationTask {

	@Override
	public boolean checkCondition(Event preEvent) {
		return true;
	}

	@Override
	public DataStream<Event> analyse(DataStream<Event> splittedStream) throws Exception {
		DataStream<Event> results = splittedStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event arg0) throws Exception {
				((PreEvent) arg0).setAuthenticated(true);
				return arg0;
			}
		}).returns(Event.class);
		return results;
	}

}
