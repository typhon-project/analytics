package auth;

import ac.york.typhon.analytics.authorizer.GenericAuthorisationTask;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

// This example task rejects all the DEBIT FNC_EV
public class AuthTaskProduct2 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("form product p select")) {
			System.out.println("It is select product");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		if (event.getQuery().contains("where p.@id == #78cdaaf1-6ae5-4f72-a5ff-94359a442708")) {
			System.out.println("It is product with p.@id == #78cdaaf1-6ae5-4f72-a5ff-94359a442708 so I reject");
			return true;
		}
		System.out.println("It is not a wrong product so I approve");
		return false;
	}
}
