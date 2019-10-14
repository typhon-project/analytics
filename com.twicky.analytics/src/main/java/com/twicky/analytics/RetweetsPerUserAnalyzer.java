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
import org.apache.flink.api.java.tuple.Tuple4;
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

public class RetweetsPerUserAnalyzer implements IAnalyzer {

	// private static Connection connection;

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream.filter(new FilterFunction<Event>() {

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
			.flatMap(new FlatMapFunction<Tuple2<TweetDTO, Date>, Tuple4<String, String, Integer, Date>>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void flatMap(Tuple2<TweetDTO, Date> incomingTuple, Collector<Tuple4<String, String, Integer, Date>> out) throws Exception {
						Integer retweetCount = NumberUtils.createInteger(incomingTuple.f0.getRetweetCount());
						out.collect(new Tuple4<String, String, Integer, Date>(incomingTuple.f0.getId(), incomingTuple.f0.getUserScreenName(), Objects.equals(retweetCount, null) ? 0 : retweetCount, incomingTuple.f1));
					}

				})
			.keyBy(0)
			.timeWindow(Time.minutes(15))
			.max(3)
			.keyBy(1)
			.timeWindow(Time.minutes(15))
			.sum(2)
			.print();

		return eventsStream;
	}

}
