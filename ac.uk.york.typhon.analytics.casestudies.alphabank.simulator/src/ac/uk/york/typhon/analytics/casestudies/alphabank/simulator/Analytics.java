package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks.TopCategoriesSum;
import ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks.TopMerchantsSum;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks.AuthorizationTask1;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class Analytics {
	
	public static void main(String[] args) throws Exception {
		TopMerchantsSum topMerchSum = new TopMerchantsSum();
		TopCategoriesSum topCatSum = new TopCategoriesSum();
		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.POST, PostEvent.class);
		

		DataStream<Event> topMerchantsStream = topMerchSum.analyse(dataStream);
		//DataStream<Event> topCategoriesStream = topCatSum.analyse(dataStream);

		//topCategoriesStream.print();

		StreamManager.initializeSink(ExternalTopicType.RESULTS, topMerchantsStream);
		//StreamManager.initializeSink(ExternalTopicType.RESULTS, topCategoriesStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);
	}

}
