package com.twicky.analytics;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

@Deprecated
public class AuthorizationAnalyzer implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream = eventsStream.filter(new FilterFunction<Event>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Event preEvent) throws Exception {

				String query = preEvent.getQuery().toLowerCase();
				if (StringUtils.isNotBlank(query)) {
					return true;
				}
				return false;

			}
		});
		return eventsStream;
	}
}
