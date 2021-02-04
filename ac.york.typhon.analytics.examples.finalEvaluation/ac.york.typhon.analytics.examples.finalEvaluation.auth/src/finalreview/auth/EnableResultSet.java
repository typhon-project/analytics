package finalreview.auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class EnableResultSet extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		// protected region EnableResultSetCheckConditionId on begin
		return true;
		// protected region EnableResultSetCheckConditionId end
	}

	@Override
	public boolean shouldIReject(Event event) {
		System.out.println(event);
		// protected region EnableResultSetShouldIRejectId on begin
		((PreEvent) event).setResultSetNeeded(true);
		//((PreEvent) event).setInvertedNeeded(true);
		//((PreEvent) event).setInvertedQuery("from VehicleWeatherData p select p.@id, p.i, p.d");
		return false;
		// protected region EnableResultSetShouldIRejectId end
	}
}