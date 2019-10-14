package com.twicky.analytics;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
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
import com.twicky.analytics.utilities.sinks.SimilarAccountsMySQLSink;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class SimilarAccountsAnalyzer implements IAnalyzer {

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
				if (query.contains("insert")) {
					return true;
				}
				return false;
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp())
		.map(new MapFunction<Event, TweetDTO>() {

			@Override
			public TweetDTO map(Event event) throws Exception {
				TweetInsertExtractor extractor = new TweetInsertExtractor(event.getQuery());
				return new TweetDTO(extractor);
			}
		})
		.map(new RichMapFunction<TweetDTO, Tuple2<RetweetedAccounts, String>>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			private RetweetedAccounts retweetedAccounts;

		    public void open(Configuration parameters) {
		    	this.retweetedAccounts = new RetweetedAccounts();
		    }

		    @Override
			public Tuple2<RetweetedAccounts, String> map(TweetDTO tweet) throws Exception {

				if (retweetedAccounts.getRetweetedAccounts().containsKey(tweet.getDiscovererScreenName())) {
					HashSet<String> currentRetweetedAccounts = retweetedAccounts.getRetweetedAccounts().get(tweet.getDiscovererScreenName());
					currentRetweetedAccounts.add(tweet.getUserScreenName());
					retweetedAccounts.getRetweetedAccounts().put(tweet.getDiscovererScreenName(), currentRetweetedAccounts);
				} else {
					HashSet<String> currentRetweetedAccounts = new HashSet<String>();
					currentRetweetedAccounts.add(tweet.getUserScreenName());
					retweetedAccounts.getRetweetedAccounts().put(tweet.getDiscovererScreenName(), currentRetweetedAccounts);
				}
				Tuple2<RetweetedAccounts, String> result = new Tuple2<RetweetedAccounts, String>(retweetedAccounts, tweet.getDiscovererScreenName());
				return result;
			}
		})
		.map(new MapFunction<Tuple2<RetweetedAccounts, String>, Tuple2<String, ArrayList<Tuple2<String, Integer>>>>() {

			@Override
			public Tuple2<String, ArrayList<Tuple2<String, Integer>>> map(Tuple2<RetweetedAccounts, String> value) throws Exception {
				// We only need to check for similarity for the discoverer of the tweet we are currently assessing. He is the only person who has his retweetedAccount changed.
				String discoverer = value.f1;
				ArrayList<Tuple2<String, Integer>> listOfSimilarAccountsWithDiscoverer = new ArrayList<Tuple2<String, Integer>>();
				// We check him against all the other discoverers we have in our list so far...
				for(String otherDiscoverer : value.f0.getRetweetedAccounts().keySet()) {
					// ...well, except himself
					if (discoverer.equals(otherDiscoverer)) {
						continue;
					} else {
						HashSet<String> tempRetweetedAccountsForDiscoverer = new HashSet<String>(value.f0.getRetweetedAccounts().get(discoverer));
						HashSet<String> retweetedAccountsForOtherDiscoverer = value.f0.getRetweetedAccounts().get(otherDiscoverer);
						tempRetweetedAccountsForDiscoverer.retainAll(retweetedAccountsForOtherDiscoverer);
						if (tempRetweetedAccountsForDiscoverer.size() >= 1) {
							Tuple2<String, Integer> commonAccount = new Tuple2<String, Integer>();
							int numberOfCommonAccounts = tempRetweetedAccountsForDiscoverer.size();
							commonAccount.f0 = otherDiscoverer;
							commonAccount.f1 = numberOfCommonAccounts;
							listOfSimilarAccountsWithDiscoverer.add(commonAccount); 
							System.out.println(discoverer + " and " + otherDiscoverer + " have " + tempRetweetedAccountsForDiscoverer.size() + " retweeters in common" + System.lineSeparator() 
								+ discoverer + " retweeted accounts: " + value.f0.getRetweetedAccounts().get(discoverer) + System.lineSeparator()
								+ otherDiscoverer + " retweeted accounts: " + retweetedAccountsForOtherDiscoverer);
						}
					}
				}
				return new Tuple2<String, ArrayList<Tuple2<String, Integer>>>(discoverer, listOfSimilarAccountsWithDiscoverer); 
			}
		})
		.filter(new FilterFunction<Tuple2<String,ArrayList<Tuple2<String,Integer>>>>() {

			@Override
			public boolean filter(Tuple2<String, ArrayList<Tuple2<String, Integer>>> value) throws Exception {
				// TODO Auto-generated method stub
				return value.f1.size() >=1;
			}
		})
		.addSink(new SimilarAccountsMySQLSink());
		return eventsStream;
	}
}
