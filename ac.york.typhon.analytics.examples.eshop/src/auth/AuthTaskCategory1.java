package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

// This example task rejects all the DEBIT FNC_EV
public class AuthTaskCategory1 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("insert category")) {
			System.out.println("It is category");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		if (event.getQuery().contains("Computer")) {
			System.out.println("It is Computer so I reject");
			return true;
		}
		System.out.println("It is not Computer so I approve");
		return false;
	}
}
