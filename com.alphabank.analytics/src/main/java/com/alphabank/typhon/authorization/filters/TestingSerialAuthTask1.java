package com.alphabank.typhon.authorization.filters;

import com.alphabank.typhon.authorization.GenericAuthorisationTask;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

// This example task rejects all the DEBIT FNC_EV
public class TestingSerialAuthTask1 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("insert into fnc_ev")) {
			System.out.println("FNC EVENT");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
//		if (event.getQuery().contains("DEBIT")) {
//			System.out.println("It is DEBIT so I reject");
//			return true;
//		}
//		System.out.println("It is not DEBIT so I approve");
		return false;
	}
}
