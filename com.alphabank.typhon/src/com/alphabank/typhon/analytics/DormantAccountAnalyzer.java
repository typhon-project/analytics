package com.alphabank.typhon.analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

import com.alphabank.typhon.dataaccess.impl.AccountActivityAccessImpl;
import com.alphabank.typhon.dto.FinancialEvent;
import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;
import com.alphabank.typhon.extractor.insert.NonFinancialEventInsertExtractor;

public class DormantAccountAnalyzer extends StreamAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception {
		
		
		eventsStream.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event preEvent) throws Exception {
				String query = preEvent.getQuery().toLowerCase();
				if (preEvent.getQuery().toLowerCase()
						.contains("insert into non_fnc_ev")) {
					
					return true;
				}
				return false;
			}
		}).map(new MapFunction<Event, Event>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Event map(Event event) throws Exception {
			
				String query = ((PostEvent) event).getPreEvent().getQuery();

				NonFinancialEventInsertExtractor nonFinancialEventInsertExtractor 
					= new NonFinancialEventInsertExtractor(query);
				System.out
				.println("Account Code : "+nonFinancialEventInsertExtractor.getAccountCode());
				if (AccountActivityAccessImpl
						.isDormantAccount(nonFinancialEventInsertExtractor)) {
					System.out
							.println("DORMANT Account Activity Detected !!! for account code :"+ nonFinancialEventInsertExtractor.getAccountCode());
				}
//				System.out.println((PostEvent) event);
				return event;
			}
		}).returns(Event.class);
		return eventsStream;
	}

}



