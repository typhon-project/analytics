package com.alphabank.typhon;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.StreamManager;

import com.alphabank.typhon.analytics.TopCategoriesCountAnalyzer;
import com.alphabank.typhon.commons.AlphaEnum;

public class TopCategoriesCountActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		TopCategoriesCountAnalyzer analyzer = new TopCategoriesCountAnalyzer();
		dataStream = analyzer.analyse(dataStream);

		StreamManager.initializeSink(AlphaEnum.ALPHA, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}
