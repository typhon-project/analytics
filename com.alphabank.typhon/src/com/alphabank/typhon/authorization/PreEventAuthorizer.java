package com.alphabank.typhon.authorization;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.JoinedStreams;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.util.OutputTag;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

import com.alphabank.typhon.authorization.filters.EventFilter;
import com.alphabank.typhon.authorization.filters.FinancialEventInsertFilter;
import com.alphabank.typhon.authorization.filters.GenericEventFilter;
import com.alphabank.typhon.authorization.filters.NonFinancialEventInsertFilter;
import com.alphabank.typhon.authorization.filters.TestingSerialAuthTask1;
import com.alphabank.typhon.authorization.filters.TestingSerialAuthTask2;
import com.alphabank.typhon.commons.AlphaEnum;

/**
 * Checks for dormant account activity
 * 
 * @author Admin
 *
 */
public class PreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
		TestingSerialAuthTask1 T1 = new TestingSerialAuthTask1();
		OutputTag<Event> task1OutputTag = new OutputTag<Event>(T1.getLabel()) {};

		TestingSerialAuthTask2 T2 = new TestingSerialAuthTask2();
		OutputTag<Event> task2OutputTag = new OutputTag<Event>(T2.getLabel()) {};

		SingleOutputStreamOperator<Event> splitStream1 = T1.run(dataStream, T1);
		SingleOutputStreamOperator<Event> splitStream2= T2.run(splitStream1.getSideOutput(task1OutputTag), T2);
		
		DataStream<Event> finalStream = splitStream2.getSideOutput(task2OutputTag);
		
		//DataStream<Event> rejected1 = splitStream1.getSideOutput(rejectedOutputTag);
		//DataStream<Event> rejected2 = splitStream2.getSideOutput(rejectedOutputTag);
		
		DataStream<Event> finalRejectedStream = splitStream1.getSideOutput(rejectedOutputTag)
				.union(splitStream2.getSideOutput(rejectedOutputTag));

		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalStream);
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}
}
