package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.JoinedStreams;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

public class PreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
			Task1 task1  = new Task1(); 
			OutputTag<Event> task1OutputTag = new OutputTag<Event>(task1.getLabel()) {};
		
			Task2 task2  = new Task2(); 
			OutputTag<Event> task2OutputTag = new OutputTag<Event>(task2.getLabel()) {};
		
		
	
		SingleOutputStreamOperator<Event> task1SplitStream = task1.run(dataStream, task1);
		
			SingleOutputStreamOperator<Event> task2SplitStream = task2.run(task1SplitStream.getSideOutput(task1OutputTag), task2);
			
		
		DataStream<Event> finalStream = task2SplitStream.getSideOutput(task2OutputTag);

		
		DataStream<Event> finalRejectedStream = task1SplitStream.getSideOutput(rejectedOutputTag).union(task2SplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
