package com.alphabank.typhon.analytics;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import com.alphabank.typhon.commons.AlphaEnum;
import com.alphabank.typhon.dataaccess.impl.AccountActivityAccessImpl;
import com.alphabank.typhon.extractor.insert.NonFinancialEventInsertExtractor;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

public class DormantAccountAnalyzer extends StreamAnalyzer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		// dataStream.print(); //print the data stream as received

		StreamManager.initializeSink(AlphaEnum.ALPHA, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception {
		eventsStream = eventsStream.map(new MapFunction<Event, Event>() {
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
