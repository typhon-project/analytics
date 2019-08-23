package com.twicky;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

import com.twicky.analytics.TweetsPerWindowAnalyzer;
import com.twicky.analytics.TweetsOverTimeAnalyzer;
import com.twicky.commons.TwickyTopics;

public class DELETE_TweetsOverTimeActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

//		TweetsOverTimeAnalyzer analyzer = new TweetsOverTimeAnalyzer();
		TweetsPerWindowAnalyzer analyzer = new TweetsPerWindowAnalyzer();
		
		
		dataStream = analyzer.analyze(dataStream);

		StreamManager.initializeSink(TwickyTopics.TWICKY, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}
