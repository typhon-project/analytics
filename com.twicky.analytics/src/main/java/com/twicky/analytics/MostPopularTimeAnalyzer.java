package com.twicky.analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import com.twicky.analytics.assigner.BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp;
import com.twicky.analytics.utilities.HourSlot;
import com.twicky.analytics.utilities.sinks.PopularHourMySQLSink;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class MostPopularTimeAnalyzer implements IAnalyzer {

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
				System.out.println("Collected from Twicky at: " + ((PostEvent) event).getPreEvent().getQueryTime());
				return new TweetDTO(extractor);
			}
		})
		.map(new MapFunction<TweetDTO, HourSlot>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public HourSlot map(TweetDTO tweet) throws Exception {

				HourSlot hourSlot = new HourSlot();
				Date date = tweet.convertCreatedAtToDate();
				if (date != null) {
					hourSlot.setHourSlot(date.getHours());
					// hourSlot.setHourSlot(hour);
				}
				return hourSlot;
			}
		})
		.flatMap(new FlatMapFunction<HourSlot, Tuple2<Integer, Integer>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(HourSlot value, Collector<Tuple2<Integer, Integer>> out) throws Exception {
				System.out.println(value.getHourSlot());

				out.collect(new Tuple2<Integer, Integer>(value.getHourSlot(), 1));
			}

		})
		.keyBy(0)
		.timeWindow(Time.minutes(1))
		.sum(1)
		.addSink(new PopularHourMySQLSink());
		//.print();
		return eventsStream;
	}
}
