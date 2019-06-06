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
public class TestingSerialAuthTask1 extends GenericAuthorisationTask {

	@Override
	public SplitStream<Event> run(DataStream<Event> preEvents) {
		SplitStream<Event> splitStream = preEvents.split(new OutputSelector<Event>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				// If I am not responsible for such event, just put them into the topic named by my name so the next AuthTask can consume it.
				if (!checkCondition(event)) {
					output.add("Task 1");
				// Else (if I am responsible for such events) call my shouldIReject method. If shouldIReject says reject, then put it in the rejected topic. 
				// If it says accept then put it in the same topic as above (named by my name) so the next filter can check it.
				// In other words, no matter what, unless an event is rejected, it always goes to a topic with my name so the next auth task can consume it
				// TODO: Some of the code here can be further abstracted so we don't ask analytics devs to write it.
				} else {
					if (shouldIReject(event)) {
						((PreEvent) event).setAuthenticated(false);  
						output.add("Rejected");
					} else {
						output.add("Task 1");
					}
				}
				return output;
			}
		});
		return null;
	}

	@Override
	public boolean checkCondition(Event event) {
		if (event.getQuery().contains("insert into fnc_ev")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shouldIReject(Event event) {
		if (event.getQuery().contains("DEBIT")) {
			return true;
		}
		return false;
	}
}
