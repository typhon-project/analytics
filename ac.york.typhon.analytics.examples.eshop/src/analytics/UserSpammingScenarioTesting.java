package analytics;

import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction.Context;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import akka.stream.impl.fusing.Map;
import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpam;

public class UserSpammingScenarioTesting implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {

		final OutputTag<Tuple3<String, String, Integer>> outputTagSpammers = new OutputTag<Tuple3<String, String, Integer>>(
				"spammers") {
		};

		SingleOutputStreamOperator<Tuple3<String, String, Integer>> sumPerUserProductStream = eventsStream
				.map(new MapFunction<Event, DeserializedPostEvent>() {

					@Override
					public DeserializedPostEvent map(Event arg0) throws Exception {
						return (DeserializedPostEvent) arg0;
					}
				}).filter(new FilterFunction<DeserializedPostEvent>() {

					@Override
					public boolean filter(DeserializedPostEvent pe) throws Exception {
						return pe.getQuery().contains("from Product p select p ");
					}
				}).map(new MapFunction<DeserializedPostEvent, Tuple3<String, String, Date>>() {

					@Override
					public Tuple3<String, String, Date> map(DeserializedPostEvent event) throws Exception {
						Date timestamp = event.getStartTime();
						String productId = event.getQuery().split("p.@id == #")[1];
						String userId = "";
						for (Slot slot : event.getPreEvent().getSlots()) {
							if (slot.getFieldName().equalsIgnoreCase("userId"))
								userId = slot.getValue().toString();
						}
						Tuple3<String, String, Date> tuple = new Tuple3<String, String, Date>(productId, userId,
								timestamp);
//						System.out.println("UserId: " + userId + " Product " + productId + " time: " + event.getStartTime());

						return tuple;
					}

				}).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorUserSpam())
////				.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Tuple3<String, String, Date>>(Time.minutes(10)) {
//
//			        @Override
//			        public long extractTimestamp(Tuple3<String, String, Date> element) {
//			            return element.f2.getTime();
//			        }
//				})
				// Tuple3<productId, UserId, Timestamp>
				.map(new MapFunction<Tuple3<String, String, Date>, Tuple3<String, String, Integer>>() {

					@Override
					public Tuple3<String, String, Integer> map(Tuple3<String, String, Date> value) throws Exception {
						return new Tuple3<String, String, Integer>(value.f0, value.f1, 1);
					}

				})
				.keyBy(0, 1)
				.sum(2);


		SingleOutputStreamOperator<Tuple3<String, String, Integer>> mainDataStream = sumPerUserProductStream
				.process(new ProcessFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>() {

					@Override
					public void processElement(Tuple3<String, String, Integer> value,
							ProcessFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>>.Context ctx,
							Collector<Tuple3<String, String, Integer>> out) throws Exception {
						if (value.f2 <= 5) {
							out.collect(value);
						} else {
							ctx.output(outputTagSpammers, value);
						}
					}
				});

		// Legit print -> 3rd task in Flink graph
		mainDataStream
		.keyBy(0)
		.timeWindow(Time.seconds(10))
		// This is used to print winowinginfo
//		.process(
//				new ProcessWindowFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>, Tuple, TimeWindow>() {
//
//					@Override
//					public void process(Tuple key,
//							ProcessWindowFunction<Tuple3<String, String, Integer>, Tuple3<String, String, Integer>, Tuple, TimeWindow>.Context ctx,
//							Iterable<Tuple3<String, String, Integer>> input,
//							Collector<Tuple3<String, String, Integer>> output) throws Exception {
//						
//						long count = 0;
//					    for (Tuple3<String, String, Integer> in: input) {
//					      count++;
//					    }
//						System.out.println("Window start: " + new Date(ctx.window().getStart()) + " end: " + new Date(ctx.window().getEnd()) + " count: " + count);
//					}
//				});
		.sum(2)
		.print();

		// Spammers print -> 2nd task in Flink graph
		mainDataStream.getSideOutput(outputTagSpammers)
		.map(new MapFunction<Tuple3<String, String, Integer>, String>() {

			@Override
			public String map(Tuple3<String, String, Integer> value) throws Exception {
				// TODO Auto-generated method stub
				return "User " + value.f1 + " is spamming product " + value.f0 + " (" + value.f2 + " times)";
			}
		})
		.print();

	}
}
