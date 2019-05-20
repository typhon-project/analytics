package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks.AuthorizationTask1;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class Authorizer {
	
	public static void main(String[] args) throws Exception {
		AuthorizationTask1 ae1 = new AuthorizationTask1();

		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		SplitStream<Event> split = dataStream.split(new OutputSelector<Event>() {

			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				if (ae1.checkCondition(event)) {
					output.add("ae1");
				} else {
					output.add("other");
				}
				return output;
			}
		});
		split.print();

		DataStream<Event> ae1Statements = split.select("ae1");
		DataStream<Event> ae1StatementsAnalysis = ae1.analyse(ae1Statements);
		
		StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION, ae1StatementsAnalysis);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
	}

}
