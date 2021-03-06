package analytics;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.commands.SelectCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import analytics.utilities.BoundedOutOfOrdernessGeneratorUserSpam;
import net.sf.jsqlparser.statement.select.Select;

public class UserSpammingScenario implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		final OutputTag<Tuple3<String, String, Integer>> outputTagSpammers = new OutputTag<Tuple3<String, String, Integer>>(
				"spammers") {
		};
		
		SingleOutputStreamOperator<Tuple3<String, String, Integer>> sumPerUserProductStream = eventsStream
				// TODO: We think we don't need this need this?
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
//						System.out.println("+UserId: " + userId + " Product " + productId + " time: " + event.getStartTime());

						return tuple;
					}

				})
				.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessGeneratorUserSpam())
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
				.timeWindow(Time.seconds(15))
				.sum(2);

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

		// Legit print -> 3rd task in Flink graph
		mainDataStream
		.keyBy(0)
		.timeWindow(Time.seconds(15))
		.sum(2)
		.map(new MapFunction<Tuple3<String,String,Integer>, String>() {

			@Override
			public String map(Tuple3<String, String, Integer> value) throws Exception {
				// TODO Auto-generated method stub
				return "Finished this at: " + sdf.format(new Date()) + " Product " + value.f1 + " visited " + value.f2 + ".";
			}
		})
		.print();

		// Spammers print -> 2nd task in Flink graph
		mainDataStream.getSideOutput(outputTagSpammers)
		.map(new MapFunction<Tuple3<String, String, Integer>, String>() {

			@Override
			public String map(Tuple3<String, String, Integer> value) throws Exception {
				return "Finish this at: " + sdf.format(new Date()) + " User " + value.f1 + " is spamming product " + value.f0 + " (" + value.f2 + " times).";
			}
		})
		.print();

	}

	
}
