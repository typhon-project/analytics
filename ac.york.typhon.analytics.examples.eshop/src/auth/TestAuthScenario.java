package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class TestAuthScenario extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldIReject(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

}
