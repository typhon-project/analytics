package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

// This example task rejects all the DEBIT FNC_EV
public class AuthTaskAddress3 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("address")) {
			System.out.println("It is address");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		if (event.getQuery().contains("Greece")) {
			System.out.println("It is Greece and I reject");
			return true;
		}
		System.out.println("It is not Greece I approve");
		return false;
	}
}
