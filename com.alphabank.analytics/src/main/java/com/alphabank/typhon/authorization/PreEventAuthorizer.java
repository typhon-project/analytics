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
		
			TestingSerialAuthTask1 testingSerialAuthTask1  = new TestingSerialAuthTask1(); 
			OutputTag<Event> testingSerialAuthTask1OutputTag = new OutputTag<Event>(testingSerialAuthTask1.getLabel()) {};
		
			TestingSerialAuthTask2 testingSerialAuthTask2  = new TestingSerialAuthTask2(); 
			OutputTag<Event> testingSerialAuthTask2OutputTag = new OutputTag<Event>(testingSerialAuthTask2.getLabel()) {};
		
		
	
		SingleOutputStreamOperator<Event> testingSerialAuthTask1SplitStream = testingSerialAuthTask1.run(dataStream, testingSerialAuthTask1)
		
			SingleOutputStreamOperator<Event> testingSerialAuthTask2SplitStream = testingSerialAuthTask2.run(testingSerialAuthTask1SplitStream.getSideOutput(testingSerialAuthTask1OutputTag), testingSerialAuthTask2)
			
		
		DataStream<Event> finalStream = testingSerialAuthTask2SplitStream.getSideOutput(testingSerialAuthTask2OutputTag);

		
		DataStream<Event> finalRejectedStream = testingSerialAuthTask1SplitStream.getSideOutput(rejectedOutputTag).union(testingSerialAuthTask2SplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalStream);
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
