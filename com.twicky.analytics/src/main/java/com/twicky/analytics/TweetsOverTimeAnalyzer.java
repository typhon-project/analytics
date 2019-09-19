package com.twicky.analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import com.twicky.analytics.utilities.sinks.PopularHourMySQLSink;
import com.twicky.analytics.watermark.BoundedOutOfOrdernessWatermark;
import com.twicky.dto.TweetDTO;
import com.twicky.extractors.insert.extractor.TweetInsertExtractor;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class TweetsOverTimeAnalyzer implements IAnalyzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//FIXME: This is wrong. I don't think that it can be implemented with the current info we have or it needs workarounds that I am not sure if they worth the pain.
	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {

		eventsStream.filter(new FilterFunction<Event>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean filter(Event preEvent) throws Exception {
				String query = preEvent.getQuery().toLowerCase();
				if (query.length() > 20) {
				}
				if (query.contains("insert")) {
					System.out.println("hi");
					return true;
				}
				return false;
			}
		}).map(new MapFunction<Event, TweetDTO>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public TweetDTO map(Event event) throws Exception {
				// System.out.println("InMap :" + event);

				String query = ((PostEvent) event).getPreEvent().getQuery();

				TweetInsertExtractor extractor = new TweetInsertExtractor(query);
				TweetDTO tweet = new TweetDTO(extractor);
				return tweet;
			}
		})
		.print();
		

		/*
		// AssignerWithPeriodicWatermarks<TweetDTO>
		// timestampAndWatermarkAssigner = new AscendingTimeStampWatermark();

		updateStatementsMappedStream.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessWatermark()).keyBy("id")
				.timeWindow(Time.days(1)).process(new MyProcessWindowFunction())
				.map(new MapFunction<Tuple3<String, String, Long>, Tuple3<String, String, Long>>() {

					@Override
					public Tuple3<String, String, Long> map(Tuple3<String, String, Long> arg0) throws Exception {
						// connection =
						// AnalyticsResultsAccessImpl.getConnection();
						//
						// String query =
						// "insert into TopMerchantsCountResults (merchant_name, month_year, count)"
						// + " values (?, ?, ?)";
						//
						// System.out.println(query);
						// // create the mysql insert preparedstatement
						// PreparedStatement preparedStmt =
						// connection.prepareStatement(query);
						// preparedStmt.setString(1, arg0.f0);
						// preparedStmt.setString(2, arg0.f1);
						// preparedStmt.setDouble(3, arg0.f2);
						//
						// // execute the preparedstatement
						// preparedStmt.execute();
						//
						// // connection.close();
						System.out.println("===============" + arg0);
						return arg0;
					}
				}).print();

		// .assignTimestampsAndWatermarks(
		// new AscendingTimeStampWatermark());

		// DataStream<TweetDTO> updateStatementsMappedStreamWatermarkedWindowed
		// = updateStatementsMappedStreamWatermarked
		// .timeWindow(Time.days(30));

		// DataStream<Event> updateStatementsMappedStreamWindowed =
		// updateStatementsStream
		// .window(TumblingEventTimeWindows.of(Time.seconds(10));

		// .every(
		// Time.of(5, TimeUnit.SECONDS));

		// updateStatementsMappedStreamWatermarked.print();

		//
		// DataStream<CarEventStatistics> carEventStatisticsDataStream =
		// carEventDataStream
		// .keyBy("carId")
		// .window(EventTimeSessionWindows.withGap(Time
		// .seconds(1)))
		// .allowedLateness(Time.seconds(2))
		// .apply(new GapWindowFunction());
		 */

		return eventsStream;
	}

}

class MyProcessWindowFunction extends ProcessWindowFunction<TweetDTO, Tuple3<String, String, Long>, Tuple, TimeWindow> {

	@Override
	public void process(Tuple key, Context context, Iterable<TweetDTO> input,
			Collector<Tuple3<String, String, Long>> out) throws Exception {
		long count = 0;
		String month = "";
		String year = "";
		for (TweetDTO in : input) {
			// month = in.getDate().toLocalDate().getMonth().toString();
			// year = Integer.toString(in.getDate().toLocalDate().getYear());
			count++;
		}
		Tuple3<String, String, Long> result = new Tuple3<String, String, Long>();
		result.f0 = (String) ((Tuple1) key).f0;

		result.f1 = month + " " + year;
		result.f2 = count;
		// System.out.println(key + " " + month + " " + year + " " + count);
		out.collect(result);

	}
}
