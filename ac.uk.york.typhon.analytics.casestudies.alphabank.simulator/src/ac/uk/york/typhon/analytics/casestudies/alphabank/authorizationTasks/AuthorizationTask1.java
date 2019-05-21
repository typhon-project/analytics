package ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.authorization.EventAuthorizationTask;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class AuthorizationTask1 extends EventAuthorizationTask {
	
	@Override
	public boolean checkCondition(Event preEvent) {
		return true;
	}

	@Override
	public Event analyse(Event event) throws Exception {
		((PreEvent) event).setAuthenticated(Math.random() < 0.5);
		return event;
	}

}
