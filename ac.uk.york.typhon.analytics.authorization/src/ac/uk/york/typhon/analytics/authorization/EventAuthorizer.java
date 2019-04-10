package ac.uk.york.typhon.analytics.authorization;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;

public class EventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.PRE, PreEvent.class);

		// dataStream.print(); //print the data stream as received

//		dataStream = dataStream.map(new MapFunction<Event, Event>() {
//			@Override
//			public Event map(Event event) throws Exception {
//				((PreEvent) event).setAuthenticated(Math.random() < 0.5);
//				System.out.println(event);
//				return event;
//			}
//		}).returns(Event.class);
		
		SplitStream<Event> split = dataStream.split(new OutputSelector<Event>() {
			
			@Override
			public Iterable<String> select(Event event) {
				List<String> output = new ArrayList<String>();
		        if (event.getQuery().toLowerCase().contains("select")) {
		            output.add("selects");
		        }
		        else {
		            output.add("other");
		        }
		        return output;
			}
		});
		
		DataStream<Event> otherStatements = split.select("other");

		StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION,
				otherStatements);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE );

	}

}
