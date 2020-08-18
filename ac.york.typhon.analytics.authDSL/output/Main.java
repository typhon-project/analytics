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
		
			AuthTaskProduct2 authTaskProduct2  = new AuthTaskProduct2(); 
			OutputTag<Event> authTaskProduct2OutputTag = new OutputTag<Event>(authTaskProduct2.getLabel()) {};
		
			AuthTaskCategory1 authTaskCategory1  = new AuthTaskCategory1(); 
			OutputTag<Event> authTaskCategory1OutputTag = new OutputTag<Event>(authTaskCategory1.getLabel()) {};
		
			AuthTaskAddress3 authTaskAddress3  = new AuthTaskAddress3(); 
			OutputTag<Event> authTaskAddress3OutputTag = new OutputTag<Event>(authTaskAddress3.getLabel()) {};
		
		
	
		SingleOutputStreamOperator<Event> authTaskCategory1SplitStream = authTaskCategory1.run(dataStream, authTaskCategory1);
		
			SingleOutputStreamOperator<Event> authTaskProduct2SplitStream = authTaskProduct2.run(authTaskCategory1SplitStream.getSideOutput(authTaskCategory1OutputTag), authTaskProduct2);
			
			SingleOutputStreamOperator<Event> authTaskAddress3SplitStream = authTaskAddress3.run(authTaskProduct2SplitStream.getSideOutput(authTaskProduct2OutputTag), authTaskAddress3);
			
		
		DataStream<Event> finalStream = authTaskAddress3SplitStream.getSideOutput(authTaskAddress3OutputTag);

		
		DataStream<Event> finalRejectedStream = authTaskProduct2SplitStream.getSideOutput(rejectedOutputTag).union(authTaskCategory1SplitStream.getSideOutput(rejectedOutputTag)).union(authTaskAddress3SplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
