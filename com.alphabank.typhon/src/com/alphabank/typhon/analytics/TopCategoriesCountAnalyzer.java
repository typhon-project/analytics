package com.alphabank.typhon.analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

import com.alphabank.typhon.analytics.assigner.BoundedOutOfOrdernessGenerator;
import com.alphabank.typhon.dto.FinancialEvent;
import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;

public class TopCategoriesCountAnalyzer extends StreamAnalyzer {

	@Override
	public DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception {
		DataStreamSink<Tuple3<String, String, Long>> results = postEvents
		.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event arg0) throws Exception {
				if (arg0.getQuery().toLowerCase().contains("insert into table fnc_ev")) {
					return true;
				}
				return false;
			}
		})
		.map(new MapFunction<Event, FinancialEvent>() {

			@Override
			public FinancialEvent map(Event event) throws Exception {
				FinancialEventInsertExtractor extractor = new FinancialEventInsertExtractor(event.getQuery());
				FinancialEvent financialEvent = new FinancialEvent(extractor);
				return financialEvent;
				
			}
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGenerator())
		.keyBy("mcgDescription")
		.timeWindow(Time.days(30))
		.process(new MyProcessWindowFunction())
		.print();
		
		return postEvents;
	}

	public class MyProcessWindowFunction extends ProcessWindowFunction<FinancialEvent, Tuple3<String,String,Long>, Tuple, TimeWindow> {

		@Override
		public void process(Tuple key, Context context, Iterable<FinancialEvent> input, Collector<Tuple3<String, String, Long>> out) throws Exception {
			long count = 0;
			String month = "";
			for (FinancialEvent in : input) {
				month = in.getDate().toLocalDate().getMonth().toString();
				count++;
			}
			Tuple3<String, String, Long> result = new Tuple3<String, String, Long>();
			result.f0 = (String)((Tuple1)key).f0;;
			result.f1 = month;
			result.f2 = count;
			System.out.println(key + " " + month + " " + count);
			out.collect(result);
			
		}

		
	}

}
