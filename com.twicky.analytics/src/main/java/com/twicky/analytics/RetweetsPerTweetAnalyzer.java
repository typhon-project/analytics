package com.twicky.analytics;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.generator.helper.Utils;

import com.twicky.analytics.assigner.BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class RetweetsPerTweetAnalyzer implements IAnalyzer {

	// private static Connection connection;

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream
				.filter(new FilterFunction<Event>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(Event postEvent) throws Exception {
						String query = postEvent.getQuery().toLowerCase();
					
						if (query.contains("update") || query.contains("insert")) {
							return true;
						}
						return false;
					}
				})
				.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp())
				.map(new MapFunction<Event, Tuple2<TweetDTO, Date>>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<TweetDTO, Date> map(Event event) throws Exception {
						
						String query = ((PostEvent) event).getPreEvent().getQuery();
						Date queryTime = ((PostEvent) event).getPreEvent().getQueryTime();
						TweetDTO tweet;

						if (query.contains("update")) {
							TweetUpdateExtractor extractor = new TweetUpdateExtractor(query);
							tweet = new TweetDTO(extractor);
							
						} else {
							TweetInsertExtractor extractor = new TweetInsertExtractor(query);
							tweet = new TweetDTO(extractor);
						}
						return new Tuple2<TweetDTO, Date>(tweet, queryTime); 

					}
				})
				.flatMap(new FlatMapFunction<Tuple2<TweetDTO, Date>, Tuple3<String, Integer, Date>>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void flatMap(Tuple2<TweetDTO, Date> incomingTuple, Collector<Tuple3<String, Integer, Date>> out) throws Exception {
						// System.out.println(value);
						Integer retweetCount = NumberUtils.createInteger(incomingTuple.f0.getRetweetCount());
						out.collect(new Tuple3<String, Integer, Date>(incomingTuple.f0.getId(), Objects.equals(retweetCount, null) ? 0 : retweetCount, incomingTuple.f1));

					}

				})
				.keyBy(0)
				.timeWindow(Time.minutes(30))
				// Get the latest value we have in this window in terms of time collected
				.max(2)
				.print();

		return eventsStream;
	}

}
