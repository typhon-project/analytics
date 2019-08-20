package com.twicky.analytics;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.process.StreamAnalyzer;
import ac.york.typhon.generator.helper.Utils;

import com.twicky.dto.TweetDTO;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class TweetsPerWindowAnalyzer extends StreamAnalyzer {

	// private static Connection connection;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream
				.filter(new FilterFunction<Event>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean filter(Event preEvent) throws Exception {
						String query = preEvent.getQuery().toLowerCase();
						// if (query.length() > 20) {
						// System.out.println(query.substring(0, 20));
						// }
						if (query.contains("update")) {
							// System.out.println(" It is an update");
							return true;
						}
						// System.out.println(" NOT an update");
						return false;
					}
				})
				.map(new MapFunction<Event, TweetDTO>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public TweetDTO map(Event event) throws Exception {

						TweetUpdateExtractor extractor = new TweetUpdateExtractor(
								event.getQuery());

						return new TweetDTO(extractor);

					}
				})

				// .map(new MapFunction<TweetDTO, Tuple2<TweetDTO, Integer>>() {
				//
				// @Override
				// public Tuple2<TweetDTO, Integer> map(TweetDTO value)
				// throws Exception {
				//
				// return new Tuple2<TweetDTO, Integer> (value, 1);
				// }
				// })

				// .assignTimestampsAndWatermarks(
				// new AssignerWithPeriodicWatermarks<Tuple2<String, Integer>>()
				// {
				.assignTimestampsAndWatermarks(
						new AssignerWithPeriodicWatermarks<TweetDTO>() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
							private final static int DELAY_IN_SECONDS = 5;

							/**
							 * Helpful link:
							 * https://vishnuviswanath.com/flink_eventtime.html
							 */
							@Override
							public long extractTimestamp(TweetDTO element,
									long previousElementTimestamp) {

								// - Here we should extract the timestamp from
								// the tweet event iself e.g. created_At
								// - Using the system timestamp is another
								// option but in this case it will
								// be the processing timestamp and not the
								// generation timestamp
								// System.out.println(element);
								return ObjectUtils.notEqual(
										element.retrieveCreatedAtTimestamp(),
										null) ? element
										.retrieveCreatedAtTimestamp()
										: new Timestamp(System
												.currentTimeMillis()).getTime();
							}

							@Override
							public Watermark getCurrentWatermark() {
								// - Label how long a message could be delayed
								// - if we are using NOW as a timestamp, it
								// means flink can go ahead and process this
								// window at the NOW instant.
								// - if a past timestamp is specified as a
								// label, it means flink will wait for delayed
								// events to arrive.

								return new Watermark(new Timestamp(System
										.currentTimeMillis()).getTime()
										- Utils.milliSeconds(DELAY_IN_SECONDS));
							}
						})
				.flatMap(
						new FlatMapFunction<TweetDTO, Tuple2<String, Integer>>() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public void flatMap(TweetDTO value,
									Collector<Tuple2<String, Integer>> out)
									throws Exception {
								out.collect(new Tuple2<String, Integer>(
										"tweetCount", 1));

							}

						})

				.keyBy(0)
				.windowAll(TumblingEventTimeWindows.of(Time.seconds(120)))
				// .timeWindow(Time.seconds(5))
				.sum(1).print();

		return eventsStream;
	}

}
