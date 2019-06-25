package com.alphabank.analytics.authorization.filters;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import com.alphabank.typhon.authorization.GenericAuthorisationTask;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

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
		if (event.getQuery().contains("DEBIT")) {
			System.out.println("It is DEBIT so I reject");
			return true;
		}
		System.out.println("It is not DEBIT so I approve");
		return false;
	}
}
