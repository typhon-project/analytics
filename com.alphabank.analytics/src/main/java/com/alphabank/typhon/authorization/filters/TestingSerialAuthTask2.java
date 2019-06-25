package com.alphabank.typhon.authorization.filters;

import com.alphabank.typhon.authorization.GenericAuthorisationTask;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

// This example task rejects all the DEBIT FNC_EV
public class TestingSerialAuthTask2 extends GenericAuthorisationTask {

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().toLowerCase().contains("insert into non_fnc_ev")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		if (!event.getQuery().contains("OOA")) {
			System.out.println("It is not OOA so I reject");
			return true;
		}
		System.out.println("It is OAA so I approve");
		return false;
	}
}
