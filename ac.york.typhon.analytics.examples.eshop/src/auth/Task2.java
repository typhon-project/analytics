package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class Task2 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		// protected region Task2CheckConditionId on begin
		return ((PreEvent) event).getQuery().contains("insert Category");
		// protected region Task2CheckConditionId end
	}

	@Override
	public boolean shouldIReject(Event event) {
		// protected region Task2ShouldIRejectId on begin
		return ((PreEvent) event).getQuery().contains("TV");
		// protected region Task2ShouldIRejectId end
	}
}