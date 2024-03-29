package com.twicky.analytics;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.generator.helper.Utils;

import com.twicky.analytics.assigner.BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp;
import com.twicky.analytics.utilities.DaySlot;
import com.twicky.analytics.utilities.FollowersOverTimeObject;
import com.twicky.analytics.utilities.HourSlot;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class MostPopularDayAnalyzer implements IAnalyzer {

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
				if (query.contains("update")) {
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
				TweetUpdateExtractor extractor = new TweetUpdateExtractor(event.getQuery());
				System.out.println("Collected from Twicky at: " + ((PostEvent) event).getPreEvent().getQueryTime());
				return new TweetDTO(extractor);
			}
		})
		.map(new MapFunction<TweetDTO, DaySlot>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public DaySlot map(TweetDTO tweet) throws Exception {

				DaySlot daySlot = new DaySlot();
				Date date = tweet.convertCreatedAtToDate();
				if (date != null) {
					daySlot.setDaySlot(date.getDay());
					// hourSlot.setHourSlot(hour);
				}
				return daySlot;
			}
		})
		.flatMap(new FlatMapFunction<DaySlot, Tuple2<Integer, Integer>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(DaySlot value, Collector<Tuple2<Integer, Integer>> out) throws Exception {
				System.out.println(value.getDaySlot());
				out.collect(new Tuple2<Integer, Integer>(value.getDaySlot(), 1));
			}

		})
		.keyBy(0)
		.timeWindow(Time.minutes(1))
		.sum(1)
		.print();
		return eventsStream;
	}
}
