package com.twicky.analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
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
				if (query.contains("insert into tweet")) {
					return true;
				}
				return false;
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorForTwickyQueryTimestamp())
		.map(new MapFunction<Event, Tuple2<TweetDTO, Date>>() {

			@Override
			public Tuple2<TweetDTO, Date> map(Event event) throws Exception {
				TweetInsertExtractor extractor = new TweetInsertExtractor(event.getQuery());
				System.out.println("Collected from Twicky at: " + ((PostEvent) event).getPreEvent().getQueryTime());
				TweetDTO tweet = new TweetDTO(extractor);
				Date queryTime = ((PostEvent) event).getPreEvent().getQueryTime();
				return new Tuple2<TweetDTO, Date>(tweet, queryTime);
			}
		})
		.map(new MapFunction<Tuple2<TweetDTO, Date>, Tuple2<HourSlot, Date>>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Tuple2<HourSlot, Date> map(Tuple2<TweetDTO, Date> tuple) throws Exception {

				HourSlot hourSlot = new HourSlot();
				Date date = tuple.f0.convertCreatedAtToDate();
				if (date != null) {
					hourSlot.setHourSlot(date.getHours());
					// hourSlot.setHourSlot(hour);
				}
				return new Tuple2<HourSlot, Date>(hourSlot, tuple.f1);
			}
		})
		.flatMap(new FlatMapFunction<Tuple2<HourSlot, Date>, Tuple3<Integer, Integer, Date>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void flatMap(Tuple2<HourSlot, Date> value, Collector<Tuple3<Integer, Integer, Date>> out) throws Exception {

				out.collect(new Tuple3<Integer, Integer, Date>(value.f0.getHourSlot(), 1, value.f1));
			}

		})
		.keyBy(0)
		.timeWindow(Time.minutes(1))
		.sum(1)
		.addSink(new PopularHourMySQLSink());
//		.print();
		return eventsStream;
	}
}
