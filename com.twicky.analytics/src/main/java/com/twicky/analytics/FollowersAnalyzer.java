package com.twicky.analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.process.StreamAnalyzer;

import com.twicky.extractors.insert.TweetInsertExtractor;
import com.twicky.extractors.update.TweetUpdateExtractor;

public class FollowersAnalyzer extends StreamAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
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
					System.out.println(query.substring(0, 20));
				}
				if (query.contains("update")) {
					System.out.println(" COntains update");
					return true;
				}
				System.out.println(" Doesn't COntain update");
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

				TweetUpdateExtractor extractor = new TweetUpdateExtractor(
						query);
				System.out.println("Tweet ID : " + extractor.getID()
						+ "   User Screen Name :"
						+ extractor.getUserScreenName());

				// System.out.println((PostEvent) event);
				return event;
			}
		}).returns(Event.class);
		return eventsStream;
	}
}