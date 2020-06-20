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

import com.alphabank.typhon.authorization.filters.EventFilter;
import com.alphabank.typhon.authorization.filters.FinancialEventInsertFilter;
import com.alphabank.typhon.authorization.filters.GenericEventFilter;
import com.alphabank.typhon.authorization.filters.NonFinancialEventInsertFilter;
import com.alphabank.typhon.authorization.filters.TestingSerialAuthTask1;
import com.alphabank.typhon.authorization.filters.TestingSerialAuthTask2;
import com.alphabank.typhon.commons.AlphaEnum;


public class PreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
			AddressAuthTask addressAuthTask  = new AddressAuthTask(); 
			OutputTag<Event> addressAuthTaskOutputTag = new OutputTag<Event>(addressAuthTask.getLabel()) {};
		
			CategoryAuthTask categoryAuthTask  = new CategoryAuthTask(); 
			OutputTag<Event> categoryAuthTaskOutputTag = new OutputTag<Event>(categoryAuthTask.getLabel()) {};
		
		
	
		SingleOutputStreamOperator<Event> addressAuthTaskSplitStream = addressAuthTask.run(dataStream, addressAuthTask)
		
			SingleOutputStreamOperator<Event> categoryAuthTaskSplitStream = categoryAuthTask.run(addressAuthTaskSplitStream.getSideOutput(addressAuthTaskOutputTag), categoryAuthTask)
			
		
		DataStream<Event> finalStream = categoryAuthTaskSplitStream.getSideOutput(categoryAuthTaskOutputTag);

		
		DataStream<Event> finalRejectedStream = addressAuthTaskSplitStream.getSideOutput(rejectedOutputTag).union(categoryAuthTaskSplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalStream);
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
