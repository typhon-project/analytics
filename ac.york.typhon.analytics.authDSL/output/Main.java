package Test;

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
		
			CCNullAuthTask cCNullAuthTask  = new CCNullAuthTask(); 
			OutputTag<Event> cCNullAuthTaskOutputTag = new OutputTag<Event>(cCNullAuthTask.getLabel()) {};
		
			CCDateAuthTask cCDateAuthTask  = new CCDateAuthTask(); 
			OutputTag<Event> cCDateAuthTaskOutputTag = new OutputTag<Event>(cCDateAuthTask.getLabel()) {};
		
			CCNumAuthTask cCNumAuthTask  = new CCNumAuthTask(); 
			OutputTag<Event> cCNumAuthTaskOutputTag = new OutputTag<Event>(cCNumAuthTask.getLabel()) {};
		
		
	
		SingleOutputStreamOperator<Event> cCNullAuthTaskSplitStream = cCNullAuthTask.run(dataStream, cCNullAuthTask);
		
			SingleOutputStreamOperator<Event> cCNumAuthTaskSplitStream = cCNumAuthTask.run(cCNullAuthTaskSplitStream.getSideOutput(cCNullAuthTaskOutputTag), cCNumAuthTask);
			
			SingleOutputStreamOperator<Event> cCDateAuthTaskSplitStream = cCDateAuthTask.run(cCNumAuthTaskSplitStream.getSideOutput(cCNumAuthTaskOutputTag), cCDateAuthTask);
			
		
		DataStream<Event> finalStream = cCDateAuthTaskSplitStream.getSideOutput(cCDateAuthTaskOutputTag);

		
		DataStream<Event> finalRejectedStream = cCNullAuthTaskSplitStream.getSideOutput(rejectedOutputTag).union(cCDateAuthTaskSplitStream.getSideOutput(rejectedOutputTag).union(cCNumAuthTaskSplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
