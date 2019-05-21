package com.alphabank.typhon;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

import com.alphabank.typhon.analytics.DormantAccountAnalyzer;
import com.alphabank.typhon.commons.AlphaEnum;

public class DormantAccountActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		DormantAccountAnalyzer analyzer = new DormantAccountAnalyzer();
		dataStream = analyzer.analyse(dataStream);

		StreamManager.initializeSink(AlphaEnum.ALPHA, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}

// DataStream<Event> dataStream = StreamManager.initializeSource(
// AnalyticTopicType.POST, PostEvent.class);
//
// // dataStream.print(); //print the data stream as received
//
// dataStream = dataStream.map(new MapFunction<Event, Event>() {
// @Override
// public Event map(Event event) throws Exception {
// String query = ((PostEvent) event).getPreEvent().getQuery();
// System.out.println(query);
// NonFinancialEventInsertExtractor nonFinancialEventInsertExtractor = new
// NonFinancialEventInsertExtractor(
// query);
// if (AccountActivityAccessImpl
// .isDormantAccount(nonFinancialEventInsertExtractor)) {
// System.out
// .println("DORMANT Account Activity Detected !!! ");
// }
// System.out.println((PostEvent) event);
// return event;
// }
// }).returns(Event.class);
//
// StreamManager.initializeSink(AlphaEnum.ALPHA, dataStream);
//
// StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);