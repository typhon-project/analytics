package com.twicky;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

import com.twicky.analytics.FollowersAnalyzer;
import com.twicky.commons.TwickyTopics;

public class DELETE_FollowersActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		FollowersAnalyzer analyzer = new FollowersAnalyzer();
		dataStream = analyzer.analyze(dataStream);

		StreamManager.initializeSink(TwickyTopics.TWICKY, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}
