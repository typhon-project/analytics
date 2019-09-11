package com.twicky.analytics;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.junit.jupiter.engine.discovery.DiscoverySelectorResolver;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.generator.helper.Utils;

import com.twicky.analytics.assigner.BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp;
import com.twicky.analytics.utilities.DaySlot;
import com.twicky.analytics.utilities.FollowersOverTimeObject;
import com.twicky.analytics.utilities.HourSlot;
import com.twicky.analytics.utilities.RetweetedAccounts;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class CommonAccountsAnalyzer implements IAnalyzer {

	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp;

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {

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
				if (query.contains("insert")) {
//					System.out.println(" It is an update");
					return true;
				}
//				System.out.println(" NOT an update");
				return false;
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp())
		.map(new MapFunction<Event, TweetDTO>() {

			@Override
			public TweetDTO map(Event event) throws Exception {
				TweetInsertExtractor extractor = new TweetInsertExtractor(event.getQuery());
//				System.out.println("Collected from Twicky at: " + ((PostEvent) event).getPreEvent().getQueryTime());
				return new TweetDTO(extractor);
			}
		})
		.map(new RichMapFunction<TweetDTO, RetweetedAccounts>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			private RetweetedAccounts retweetedAccounts;

		    public void open(Configuration parameters) {
		    	this.retweetedAccounts = new RetweetedAccounts();
		    }

			@Override
			public RetweetedAccounts map(TweetDTO tweet) throws Exception {

//				System.out.println("Tweet id: " + tweet.getId() + " Text: " + tweet.getText() + 
//						 " Original Poster: " + tweet.getUserScreenName() + " collected from Twicky by: " + tweet.getDiscovererScreenName());

				if (retweetedAccounts.getRetweetedAccounts().containsKey(tweet.getDiscovererScreenName())) {
					HashSet<String> currentRetweetedAccounts = retweetedAccounts.getRetweetedAccounts().get(tweet.getDiscovererScreenName());
					currentRetweetedAccounts.add(tweet.getUserScreenName());
					retweetedAccounts.getRetweetedAccounts().put(tweet.getDiscovererScreenName(), currentRetweetedAccounts);
				} else {
					HashSet<String> currentRetweetedAccounts = new HashSet<String>();
					currentRetweetedAccounts.add(tweet.getUserScreenName());
					retweetedAccounts.getRetweetedAccounts().put(tweet.getDiscovererScreenName(), currentRetweetedAccounts);
				}
				
				return retweetedAccounts;
			}
		})
		.map(new MapFunction<RetweetedAccounts, String>() {

			@Override
			public String map(RetweetedAccounts value) throws Exception {
//				System.out.println(value.getRetweetedAccounts());
				for(String discoverer : value.getRetweetedAccounts().keySet()) {
					for(String otherDiscoverer : value.getRetweetedAccounts().keySet()) {
						if (discoverer.equals(otherDiscoverer)) {
//							System.out.println("I went into if for " + discoverer + " and " + otherDiscoverer);
						} else {
							HashSet<String> tempRetweetedAccountsForDiscoverer = new HashSet<String>(value.getRetweetedAccounts().get(discoverer));
							HashSet<String> retweetedAccountsForOtherDiscoverer = value.getRetweetedAccounts().get(otherDiscoverer);
							tempRetweetedAccountsForDiscoverer.retainAll(retweetedAccountsForOtherDiscoverer);
							if (tempRetweetedAccountsForDiscoverer.size() >= 1) {
								System.out.println(discoverer + " and " + otherDiscoverer + " have " + tempRetweetedAccountsForDiscoverer.size() + " retweeters in common" + System.lineSeparator() 
									+ discoverer + " retweeted accounts: " + value.getRetweetedAccounts().get(discoverer) + System.lineSeparator()
									+ otherDiscoverer + " retweeted accounts: " + retweetedAccountsForOtherDiscoverer);
							}
						}
					}
				}
				return "";
			}
		});
		return eventsStream;
	}
}
