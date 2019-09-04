package com.twicky.analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

import com.twicky.analytics.utilities.FollowersOverTimeObject;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class FollowersOverTimeAnalyzer implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream.filter(new FilterFunction<Event>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Event preEvent) throws Exception {
				String query = preEvent.getQuery().toLowerCase();
				if (query.length() > 20) {
//					System.out.println(query.substring(0, 20));
				}
				if (query.contains("update")) {
//					System.out.println(" It is an update");
					return true;
				}
//				System.out.println(" NOT an update");
				return false;
			}
		}).map(new MapFunction<Event, FollowersOverTimeObject>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public FollowersOverTimeObject map(Event event) throws Exception {

				String query = ((PostEvent) event).getPreEvent().getQuery();

				TweetUpdateExtractor extractor = new TweetUpdateExtractor(query);
				
				FollowersOverTimeObject fOTO = new FollowersOverTimeObject();
				fOTO.setCreatedAt(extractor.getCreatedAt());
				fOTO.setUserId(extractor.getUserId());
				fOTO.setFollowersCount(extractor.getFollowersCount());
				
				return fOTO;
			}
		})
		.print();
		return eventsStream;
	}
}
