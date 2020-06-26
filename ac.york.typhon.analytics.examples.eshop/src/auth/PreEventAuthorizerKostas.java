package auth;

import java.util.Date;
import java.util.UUID;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

public class PreEventAuthorizerKostas {

	public static void main(String[] args) throws Exception {
		System.out.println("Started At: " + new Date());
		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class,
				UUID.randomUUID().toString());

		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {
		};

		CCNullAuthTask cCNullAuthTask = new CCNullAuthTask();
		OutputTag<Event> cCNullAuthTaskOutputTag = new OutputTag<Event>(cCNullAuthTask.getLabel()) {
		};

		CCDateAuthTask cCDateAuthTask = new CCDateAuthTask();
		OutputTag<Event> cCDateAuthTaskOutputTag = new OutputTag<Event>(cCDateAuthTask.getLabel()) {
		};

		CCNumAuthTask cCNumAuthTask = new CCNumAuthTask();
		OutputTag<Event> cCNumAuthTaskOutputTag = new OutputTag<Event>(cCNumAuthTask.getLabel()) {
		};

		SingleOutputStreamOperator<Event> cCNullAuthTaskSplitStream = cCNullAuthTask.run(dataStream, cCNullAuthTask);

		SingleOutputStreamOperator<Event> cCNumAuthTaskSplitStream = cCNumAuthTask
				.run(cCNullAuthTaskSplitStream.getSideOutput(cCNullAuthTaskOutputTag), cCNumAuthTask);

		SingleOutputStreamOperator<Event> cCDateAuthTaskSplitStream = cCDateAuthTask
				.run(cCNumAuthTaskSplitStream.getSideOutput(cCNumAuthTaskOutputTag), cCDateAuthTask);

		DataStream<Event> finalStream = cCDateAuthTaskSplitStream.getSideOutput(cCDateAuthTaskOutputTag);

		DataStream<Event> finalRejectedStream = cCNullAuthTaskSplitStream.getSideOutput(rejectedOutputTag)
				.union(cCDateAuthTaskSplitStream.getSideOutput(rejectedOutputTag)
						.union(cCNumAuthTaskSplitStream.getSideOutput(rejectedOutputTag)));

		// only see accepted/rejected orders
		finalStream = finalStream.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event event) throws Exception {
				System.out.println("Finished this at: " + new Date());
				return event;
			}
		});
		finalRejectedStream = finalRejectedStream.map(new MapFunction<Event, Event>() {
			@Override
			public Event map(Event event) throws Exception {
				System.out.println("Finished this at: " + new Date());
				return event;
			}
		});

//		finalStream.print();

		// finalRejectedStream.print();

		StreamManager.initializeSink(AnalyticTopicType.AUTH, finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH, finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}
}
