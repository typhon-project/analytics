package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationfilters.FinancialEventAuthorization;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class Authorizer {
	
	
	public static String FINANCIAL_EVENTS_FILTERED_TOPIC_NAME = "financial_events_filtered_topic";
	public static String OTHERS_TOPIC_NAME = "others_topic";
	
	public static void main(String[] args) throws Exception {
		FinancialEventAuthorization financialEventAuthorization = new FinancialEventAuthorization();

		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		SplitStream<Event> splitStream = dataStream.split(new OutputSelector<Event>() {

			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				if (financialEventAuthorization.checkCondition(event)) {
					output.add(FINANCIAL_EVENTS_FILTERED_TOPIC_NAME);
				} else {
					output.add(OTHERS_TOPIC_NAME);
				}
				return output;
			}
		});
		splitStream.print();

		DataStream<Event> financialEventsStream = splitStream.select(FINANCIAL_EVENTS_FILTERED_TOPIC_NAME);
		
			
		DataStream<Event> ae1StatementsAnalysis = financialEventAuthorization.analyse(financialEventsStream);
		
		StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION, ae1StatementsAnalysis);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
	}

}
