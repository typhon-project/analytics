package ac.uk.york.typhon.analytics.casestudies.alphabank.simulator;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks.AuthorizationTask1;
import ac.uk.york.typhon.analytics.casestudies.alphabank.authorizationTasks.AuthorizeAllTask;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class Authorizer {
	
	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		AuthorizeAllTask authAllTask = new AuthorizeAllTask();
		AuthorizationTask1 authTask1 = new AuthorizationTask1();
		/* Using old split architecture
		SplitStream<Event> split = dataStream.split(new OutputSelector<Event>() {

			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				if (authAllTask.checkCondition(event)) {
					output.add("all");
				} else {
					output.add("other");
				}
				return output;
			}
		});
		//split.print();

		DataStream<Event> authAllStatements = split.select("all");
		DataStream<Event> authAllStatementsAnalysis = authAllTask.analyse(authAllStatements);
		StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION, authAllStatementsAnalysis);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		*/
		
		// Using new architecture
		SplitStream<Event> taskAll = dataStream.
		split(new OutputSelector<Event>() {
			
			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				System.out.println("Event: " + event.getId() + " " + ((PreEvent) event).isAuthenticated());
				if (authAllTask.checkCondition(event)) {
					System.out.println("Task All");
					try {
						authAllTask.analyse(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (((PreEvent) event).isAuthenticated()) {
						output.add("taskAll");
					} else {
						output.add("rejected");
					}
				}
				System.out.println("Event: " + event.getId() + " " + ((PreEvent) event).isAuthenticated());
				return output;
			}
		});
		
		SplitStream<Event> task1 = taskAll.select("taskAll").split(new OutputSelector<Event>() {
			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
				System.out.println("Event: " + event.getId() + " " + ((PreEvent) event).isAuthenticated());
				if (authTask1.checkCondition(event)) {
					System.out.println("Task 1");
					try {
						authTask1.analyse(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (((PreEvent) event).isAuthenticated()) {
						output.add("task1");
					} else {
						output.add("rejected");
					}
				}
				System.out.println("Event: " + event.getId() + " " + ((PreEvent) event).isAuthenticated());
				return output;
			}
		});
		
		//StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION, authAllStatementsAnalysis);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}

}
