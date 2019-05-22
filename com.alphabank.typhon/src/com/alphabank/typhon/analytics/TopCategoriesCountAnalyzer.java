package com.alphabank.typhon.analytics;

import java.util.ArrayList;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;

import com.alphabank.typhon.analytics.assigner.BoundedOutOfOrdernessGenerator;
import com.alphabank.typhon.dto.FinancialEvent;
import com.alphabank.typhon.extractor.insert.FinancialEventInsertExtractor;


import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.process.StreamAnalyzer;


public class TopCategoriesCountAnalyzer extends StreamAnalyzer {

	@Override
	public DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception {
//		DataStreamSink<ArrayList<Tuple3<String, String, Long>>> results = 
		postEvents
		.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event preEvent) throws Exception {
				String query = preEvent.getQuery().toLowerCase();
				if (preEvent.getQuery().toLowerCase()
						.contains("insert into fnc_ev")) {
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
		.addSink(new SinkFunction<Tuple3<String,String,Long>>() {
			
			@Override
			public void invoke(Tuple3<String, String, Long> value) throws Exception {
				// TODO Auto-generated method stub
				SinkFunction.super.invoke(value);
				ArrayList<Tuple3<String,String,Long>> allTuples = new ArrayList<Tuple3<String,String,Long>>();
				allTuples.add(value);
				System.out.println("Value: " +  value);
			}
		});
		
//		.map(new RichMapFunction<Tuple3<String,String,Long>, ArrayList<Tuple3<String,String,Long>>>() {
//
//			ArrayList<Tuple3<String,String,Long>> allTuples;
//			
//			@Override
//			public void open(Configuration parameters) throws Exception {
//				super.open(parameters);
//				allTuples = new ArrayList<Tuple3<String,String,Long>>();
//			}
//			
//			@Override
//			public ArrayList<Tuple3<String, String, Long>> map(Tuple3<String, String, Long> arg0) throws Exception {
//				allTuples.add(arg0);
//				return allTuples;
//			}
//		})
//		.print();
		
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
//			System.out.println(key + " " + month + " " + count);
			out.collect(result);
			
		}
	}
	
	public class CategoriesCountTuple implements Comparable<CategoriesCountTuple> {
	    private final String name;
	    private final String month;
	    private final Long count;

	    public CategoriesCountTuple(String name, String month, long count) {
	        this.name = name;
	        this.month = month;
	        this.count = count;
	    }

	    public String name() { return name;  }
	    public String month() { return month;  }
	    public long count()   { return count; }

	    public int compareTo(CategoriesCountTuple o) {
	        return this.count.compareTo(o.count);
	    }
	}

}
