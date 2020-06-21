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
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpam;

public class UserSpammingSetScenario {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		DataStream<String> datastream = env.readTextFile("resources/userSpammingTestInput.txt");
//		DataStream<Tuple3<String, String, Integer>> tupleStream = 

		DataStream<Tuple3<String, String, Integer>> sumPerUserProductStream = datastream
				.map(new MapFunction<String, Tuple3<String, String, Date>>() {

					@Override
					public Tuple3<String, String, Date> map(String value) throws Exception {
						ArrayList<String> tupleList = new ArrayList<>(Arrays.asList(value.split(",")));
						Date timestamp = new Date(Long.parseLong(tupleList.get(2)));
						Tuple3<String, String, Date> tuple = new Tuple3<String, String, Date>(tupleList.get(0),
								tupleList.get(1), timestamp);
						return tuple;
					}

				}).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorUserSpam())
				.map(new MapFunction<Tuple3<String, String, Date>, Tuple3<String, String, Integer>>() {

					@Override
					public Tuple3<String, String, Integer> map(Tuple3<String, String, Date> value) throws Exception {
						return new Tuple3<String, String, Integer>(value.f0, value.f1, 1);
					}

				})
				.keyBy(0, 1)
				.timeWindow(Time.seconds(20))
				.sum(2);
//		.split(new OutputSelector<Tuple3<String, String, Integer>>() {
//		    @Override
//		    public Iterable<String> select(Tuple3<String, String, Integer> value) {
//		        List<String> output = new ArrayList<String>();
//		        if (value.f2 > 2) {
//		            output.add("spammer");
//		        }
//		        else {
//		            output.add("good lad");
//		        }
//		        return output;
//		    }
//		});

//		splitStream.select("good lad")
//		.filter(new FilterFunction<Tuple3<String, String, Integer>>() {
//			
//			@Override
//			public boolean filter(Tuple3<String, String, Integer> value) throws Exception {
//				
//				return value.f2<=2;
//			}
//		})
//		.keyBy(0)
//		.timeWindow(Time.seconds(20))
//		.sum(2)
//		.print();

//		splitStream.select("spammer").map(new MapFunction<Tuple3<String,String,Integer>, String>() {
//
//			@Override
//			public String map(Tuple3<String, String, Integer> value) throws Exception {
//				// TODO Auto-generated method stub
//				return "User " + value.f1 + " is spamming product " + value.f0 + " (" + value.f2 + " times)";
//			}
//		})
//		.print();

		final OutputTag<Tuple3<String, String, Integer>> outputTagSpammers = new OutputTag<Tuple3<String, String, Integer>>(
				"spammers") {
		};

		SingleOutputStreamOperator<Tuple3<String, String, Integer>> mainDataStream = sumPerUserProductStream
				.process(new ProcessFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>() {

					@Override
					public void processElement(Tuple3<String, String, Integer> value,
							ProcessFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>.Context ctx,
							Collector<Tuple3<String, String, Integer>> out) throws Exception {
						if (value.f2 <= 2) {
							out.collect(value);
						} else {
							ctx.output(outputTagSpammers, value);
						}
					}
				});
		
		mainDataStream
		.keyBy(0)
		.timeWindow(Time.seconds(20))
		.sum(2)
		.print();
		
		mainDataStream.getSideOutput(outputTagSpammers).map(new MapFunction<Tuple3<String,String,Integer>, String>() {

			@Override
			public String map(Tuple3<String, String, Integer> value) throws Exception {
				// TODO Auto-generated method stub
				return "User " + value.f1 + " is spamming product " + value.f0 + " (" + value.f2 + " times)";
			}
		})
		.print();
		env.execute();
	}
}
