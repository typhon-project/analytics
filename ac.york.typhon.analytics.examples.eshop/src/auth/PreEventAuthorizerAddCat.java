package auth;
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

public class PreEventAuthorizerAddCat {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
			AddressAuthTask addressAuthTask  = new AddressAuthTask(); 
			OutputTag<Event> addressAuthTaskOutputTag = new OutputTag<Event>(addressAuthTask.getLabel()) {};
		
			CategoryAuthTask categoryAuthTask  = new CategoryAuthTask(); 
			OutputTag<Event> categoryAuthTaskOutputTag = new OutputTag<Event>(categoryAuthTask.getLabel()) {};
		
		SingleOutputStreamOperator<Event> addressAuthTaskSplitStream = addressAuthTask.run(dataStream, addressAuthTask);
		
			SingleOutputStreamOperator<Event> categoryAuthTaskSplitStream = categoryAuthTask.run(addressAuthTaskSplitStream.getSideOutput(addressAuthTaskOutputTag), categoryAuthTask);
			
		
		DataStream<Event> finalStream = categoryAuthTaskSplitStream.getSideOutput(categoryAuthTaskOutputTag);
		finalStream.print();
		
		DataStream<Event> finalRejectedStream = addressAuthTaskSplitStream.getSideOutput(rejectedOutputTag).union(categoryAuthTaskSplitStream.getSideOutput(rejectedOutputTag));
		finalRejectedStream.print();
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
