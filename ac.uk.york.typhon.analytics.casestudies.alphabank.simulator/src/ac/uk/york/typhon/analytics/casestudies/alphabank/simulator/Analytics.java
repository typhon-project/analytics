package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
//import ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks.TopCategories;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks.AuthorizationTask1;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class Analytics {
	
	public static void main(String[] args) throws Exception {
		TopCategories top = new TopCategories();
		TopProducts prod = new 
		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.POST, PostEvent.class);
		

		DataStream<Event> topCategoriesStream = top.analyse(dataStream);
		topCategoriesStream.print();
		
		DataStream<Event> topCategoriesStream = prod.analyse(dataStream);
		topCategoriesStream.print();

		StreamManager.initializeSink(ExternalTopicType.RESULTS, topCategoriesStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.POST);
	}

}
