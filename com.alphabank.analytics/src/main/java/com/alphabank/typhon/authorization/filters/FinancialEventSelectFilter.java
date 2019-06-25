package com.alphabank.typhon.authorization.filters;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public class FinancialEventSelectFilter extends EventFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean checkCondition(Event preEvent) {

		String query = preEvent.getQuery().toLowerCase();
		if (query.contains("select") && query.contains("fnc_ev")) {

			return true;
		} else {
			return false;
		}
	}

	@Override
	public DataStream<Event> analyse(DataStream<Event> splittedStream)
			throws Exception {

		DataStream<Event> results = splittedStream.map(
				new MapFunction<Event, Event>() {

					/**
			 * 
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public Event map(Event arg0) throws Exception {
						((PreEvent) arg0).setAuthenticated(true);
						return arg0;
					}
				}).returns(Event.class);
		return results;
	}

}
