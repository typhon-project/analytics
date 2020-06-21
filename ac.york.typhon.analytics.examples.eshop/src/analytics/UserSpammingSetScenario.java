package analytics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpam;

public class UserSpammingSetScenario {
	
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		
		DataStream<String> datastream = env.readTextFile("resources/userSpammingTestInput.txt");
//		DataStream<Tuple3<String, String, Integer>> tupleStream = 
				
		datastream
		.map(new MapFunction<String, Tuple3<String, String, Date>>() {

			@Override
			public Tuple3<String, String, Date> map(String value) throws Exception {
				ArrayList<String> tupleList = new ArrayList<>(Arrays.asList(value.split(",")));
				Date timestamp = new Date(Long.parseLong(tupleList.get(2)));
				Tuple3<String, String, Date> tuple = new Tuple3<String, String, Date>(tupleList.get(0), tupleList.get(1), timestamp);
				return tuple;
			}
		
		})
		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorUserSpam())
		.map(new MapFunction<Tuple3<String,String,Date>, Tuple3<String,String,Integer>>() {

			@Override
			public Tuple3<String, String, Integer> map(Tuple3<String, String, Date> value) throws Exception {
				return new Tuple3<String, String, Integer>(value.f0, value.f1, 1);
			}
			
		})
		.keyBy(0,1)
		.timeWindow(Time.seconds(20))
		.sum(2)
		.filter(new FilterFunction<Tuple3<String, String, Integer>>() {
			
			@Override
			public boolean filter(Tuple3<String, String, Integer> value) throws Exception {
				
				return value.f2<=2;
			}
		})
		.keyBy(0)
		.timeWindow(Time.seconds(20))
		.sum(2)
		.print();
		
//		Pattern<Tuple3<String, String, Date>, Tuple3<String, String, Date>> examplePattern = Pattern.<Tuple3<String, String, Date>>begin("start")
//				.times(0,1);
//		
//	
//		PatternStream<Tuple3<String, String, Date>> patternStream = CEP.pattern(tupleStream, examplePattern);
//		patternStream.select(new PatternSelectFunction<Tuple3<String, String, Date>, String>() {
//
//			@Override
//			public String select(Map<String, List<Tuple3<String, String, Date>>> arg0) throws Exception {
//				System.out.println(arg0);
////				String message = "User " + arg0.get("start").f1 + " spammed " + arg0.f0;
//				return "";
//			}
//
//			
//			
//		});
		env.execute();
	}
}
