package com.twicky.analytics;

import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.process.StreamAnalyzer;

import com.twicky.dto.TweetDTO;
import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class TweetsOverTimeAnalyzer extends StreamAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception {

		DataStream<Event> updateStatementsStream = eventsStream
				.filter(new FilterFunction<Event>() {

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
							System.out.println(" It is an update");
							return true;
						}
						System.out.println(" NOT an update");
						return false;
					}
				});

		DataStream<TweetDTO> updateStatementsMappedStream = updateStatementsStream
				.map(new MapFunction<Event, TweetDTO>() {
					/**
			 * 
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public TweetDTO map(Event event) throws Exception {
						// System.out.println("InMap :" + event);

						String query = ((PostEvent) event).getPreEvent()
								.getQuery();

						TweetUpdateExtractor extractor = new TweetUpdateExtractor(
								query);
						TweetDTO dto = new TweetDTO(extractor);
						// System.out.println("Tweet ID : " + extractor.getID()
						// + "   User Screen Name :"
						// + extractor.getUserScreenName());
						// System.out.println(extractor);

						// System.out.println((PostEvent) event);
						return dto;
					}
				}).returns(TweetDTO.class);

		// DataStream<Event> updateStatementsMappedStreamWindowed =
		// updateStatementsStream
		// .window(TumblingEventTimeWindows.of(Time.seconds(10));

		// .every(
		// Time.of(5, TimeUnit.SECONDS));

		updateStatementsMappedStream.print();

		//
		// DataStream<CarEventStatistics> carEventStatisticsDataStream =
		// carEventDataStream
		// .keyBy("carId")
		// .window(EventTimeSessionWindows.withGap(Time
		// .seconds(1)))
		// .allowedLateness(Time.seconds(2))
		// .apply(new GapWindowFunction());

		return eventsStream;
	}
}
