package com.alphabank.typhon.authorization.filters;

import java.io.Serializable;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

public abstract class EventFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract boolean checkCondition(Event preEvent);

	public DataStream<Event> analyse(DataStream<Event> splittedStream)
			throws Exception {

		DataStream<Event> results = splittedStream.map(
				new MapFunction<Event, Event>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Event map(Event event) throws Exception {
						((PreEvent) event).setAuthenticated(true);
						return event;
					}
				}).returns(Event.class);
		return results;
	}

	public String getLabel() {
		return this.getClass().getName();
	}
}
