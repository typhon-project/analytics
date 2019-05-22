package com.alphabank.typhon.analytics;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

import com.alphabank.typhon.dataaccess.impl.AccountActivityAccessImpl;
import com.alphabank.typhon.extractor.insert.NonFinancialEventInsertExtractor;

public class DormantAccountAnalyzer extends StreamAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception {
		eventsStream = eventsStream.map(new MapFunction<Event, Event>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Event map(Event event) throws Exception {
				String query = ((PostEvent) event).getPreEvent().getQuery();
				System.out.println(query);
				NonFinancialEventInsertExtractor nonFinancialEventInsertExtractor = new NonFinancialEventInsertExtractor(
						query);
				if (AccountActivityAccessImpl
						.isDormantAccount(nonFinancialEventInsertExtractor)) {
					System.out
							.println("DORMANT Account Activity Detected !!! ");
				}
				System.out.println((PostEvent) event);
				return event;
			}
		}).returns(Event.class);
		return eventsStream;
	}

}
