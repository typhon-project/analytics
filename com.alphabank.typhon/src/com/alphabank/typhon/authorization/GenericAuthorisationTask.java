package com.alphabank.typhon.authorization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

public abstract class GenericAuthorisationTask implements Serializable {

	public SplitStream<Event> run(DataStream<Event> preEvents, GenericAuthorisationTask task) {

		SplitStream<Event> splitStream = preEvents.split(new OutputSelector<Event>() {


			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				// If I am not responsible for such event, just put them into the topic named by
				// my name so the next AuthTask can consume it.
				if (!task.checkCondition(event)) {
					System.out.println(task.getLabel() + " is not responsible for this event (" + event.getId() + ") "
							+ event.getQuery());
					output.add(task.getLabel());
					// Else (if I am responsible for such events) call my shouldIReject method. If
					// shouldIReject says reject, then put it in the rejected topic.
					// If it says accept then put it in the same topic as above (named by my name)
					// so the next filter can check it.
					// In other words, no matter what, unless an event is rejected, it always goes
					// to a topic with my name so the next auth task can consume it
					// TODO: Some of the code here can be further abstracted so we don't ask
					// analytics devs to write it.
				} else {
					System.out.println(task.getLabel() + " is responsible for this event (" + event.getId() + ") "
							+ event.getQuery());
					if (task.shouldIReject(event)) {
						System.out.println(
								task.getLabel() + " rejects this event (" + event.getId() + ") " + event.getQuery());
						((PreEvent) event).setAuthenticated(false);
						output.add("Rejected");
					} else {
						System.out.println(
								task.getLabel() + " approves this event (" + event.getId() + ") " + event.getQuery());
						output.add(task.getLabel());
					}
				}
				return output;
			}
		});

		return splitStream;
	}

	public abstract boolean checkCondition(Event event);

	public abstract boolean shouldIReject(Event event);

	public String getLabel() {
		return this.getClass().getName();
	}

}
