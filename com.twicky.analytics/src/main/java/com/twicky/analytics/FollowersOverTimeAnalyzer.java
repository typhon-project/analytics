package com.twicky.analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

import com.twicky.analytics.utilities.FollowersOverTimeObject;
import com.twicky.analytics.utilities.sinks.FollowersOverTimeMySQLSink;
import com.twicky.analytics.utilities.sinks.PopularHourMySQLSink;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;
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
				if (query.contains("update tweet") || query.contains("insert into tweet")) {
					return true;
				}
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
				System.out.println(query);
				Date queryTime = ((PostEvent) event).getPreEvent().getQueryTime();
				FollowersOverTimeObject fOTO = new FollowersOverTimeObject();
				if (query.contains("update")) {
					TweetUpdateExtractor extractor = new TweetUpdateExtractor(query);
					fOTO.setCreatedAt(extractor.getCreatedAt());
					fOTO.setUserId(extractor.getUserId());
					fOTO.setFollowersCount(extractor.getFollowersCount());
					fOTO.setUserScreenName(extractor.getUserScreenName());
					fOTO.setQueryTime(queryTime);
				} else {
					TweetInsertExtractor extractor = new TweetInsertExtractor(query);
					fOTO.setCreatedAt(extractor.getCreatedAt());
					fOTO.setUserId(extractor.getUserId());
					fOTO.setFollowersCount(extractor.getFollowersCount());
					fOTO.setUserScreenName(extractor.getUserScreenName());
					fOTO.setQueryTime(queryTime);

				}
				System.out.println("hi: " + fOTO.getFollowersCount());
				return fOTO;
			}
		})
		.map(new MapFunction<FollowersOverTimeObject, Tuple3<String, Integer, Date>>() {

			@Override
			public Tuple3<String, Integer, Date> map(FollowersOverTimeObject fOTO) throws Exception {
				int followersCount;
				if (fOTO.getFollowersCount().equals("") || fOTO.getFollowersCount() == null) {
					followersCount = 0;
				} else {
					followersCount = Integer.parseInt(fOTO.getFollowersCount());
				}
				return new Tuple3<String, Integer, Date>(fOTO.getUserScreenName(), followersCount, fOTO.getQueryTime());
			}
			
		})
		.addSink(new FollowersOverTimeMySQLSink());
//		.print();
		return eventsStream;
	}
}
