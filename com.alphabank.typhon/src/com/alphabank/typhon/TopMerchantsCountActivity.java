package com.alphabank.typhon;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

import com.alphabank.typhon.analytics.TopMerchantsCountAnalyzer;
import com.alphabank.typhon.commons.AlphaEnum;

public class TopMerchantsCountActivity {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.POST, PostEvent.class);

		TopMerchantsCountAnalyzer analyzer = new TopMerchantsCountAnalyzer();
		dataStream = analyzer.analyse(dataStream);

		StreamManager.initializeSink(AlphaEnum.ALPHA, dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);

	}

}
