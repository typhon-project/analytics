package com.alphabank.typhon.authorization.filters;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import com.alphabank.typhon.authorization.GenericAuthorisationTask;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

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
