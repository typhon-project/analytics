package com.twicky;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.StreamManager;

import com.twicky.analytics.TweetsOverTimeAnalyzer;
import com.twicky.commons.TwickyTopics;

public class TweetsOverTimeActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		TweetsOverTimeAnalyzer analyzer = new TweetsOverTimeAnalyzer();
		dataStream = analyzer.analyse(dataStream);

		StreamManager.initializeSink(TwickyTopics.TWICKY, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}
