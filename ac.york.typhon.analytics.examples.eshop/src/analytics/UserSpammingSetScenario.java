package analytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.serialization.EventSchema;
import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpam;
import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpamTuple4;
import infra.RunSimulator;

public class UserSpammingSetScenario {

	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty("auto.offset.reset", "earliest");

		DataStream<String> datastream = env.addSource(
				new FlinkKafkaConsumer<String>("DATA", new SimpleStringSchema(), properties));

//		DataStream<String> datastream = env.addSource(new SourceFunction<String>() {
//
//			@Override
//			public void run(SourceContext<String> ctx) throws Exception {
//				try {
//					File myObj = new File("resources/userSpammingTestInput.txt");
//					Scanner myReader = new Scanner(myObj);
//					while (myReader.hasNextLine()) {
//						String data = myReader.nextLine();
//						ctx.collect(data);
//						ctx.
//						
//					}
//					myReader.close();
//				} catch (FileNotFoundException e) {
//					System.out.println("An error occurred.");
//					e.printStackTrace();
//				}
//				
//			}
//
//			@Override
//			public void cancel() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		
		
				
//		DataStream<String> datastream = env.readTextFile("resources/userSpammingTestInput.txt");
//		DataStream<Tuple3<String, String, Integer>> tupleStream = 

		DataStream<Tuple4<String, String, Integer,Date>> sumPerUserProductStream = datastream
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
				.map(new MapFunction<Tuple3<String, String, Date>, Tuple4<String, String, Integer, Date>>() {

					@Override
					public Tuple4<String, String, Integer, Date> map(Tuple3<String, String, Date> value) throws Exception {
						return new Tuple4<String, String, Integer, Date>(value.f0, value.f1, 1, value.f2);
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

		final OutputTag<Tuple4<String, String, Integer, Date>> outputTagSpammers = new OutputTag<Tuple4<String, String, Integer, Date>>(
				"spammers") {
		};

		SingleOutputStreamOperator<Tuple4<String, String, Integer, Date>> mainDataStream = sumPerUserProductStream
				.process(new ProcessFunction<Tuple4<String, String, Integer, Date>, Tuple4<String, String, Integer, Date>>() {

					@Override
					public void processElement(Tuple4<String, String, Integer, Date> value,
							ProcessFunction<Tuple4<String, String, Integer, Date>, Tuple4<String, String, Integer, Date>>.Context ctx,
							Collector<Tuple4<String, String, Integer, Date>> out) throws Exception {
						if (value.f2 <= 2) {
							out.collect(value);
						} else {
							ctx.output(outputTagSpammers, value);
						}
					}
				});
		
		mainDataStream
//		.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorUserSpamTuple4())
		.keyBy(0)
		.timeWindow(Time.seconds(20))
		.sum(2)
		.print();
		
		mainDataStream.getSideOutput(outputTagSpammers).map(new MapFunction<Tuple4<String,String,Integer, Date>, String>() {

			@Override
			public String map(Tuple4<String, String, Integer, Date> value) throws Exception {
				// TODO Auto-generated method stub
				return "User " + value.f1 + " is spamming product " + value.f0 + " (" + value.f2 + " times)";
			}
		})
		.print();
		env.execute();
	}
	
}
