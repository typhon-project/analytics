package ac.uk.york.typhon.analytics.authorization;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.authorization.commons.enums.ExternalTopicType;
import ac.uk.york.typhon.analytics.commons.Constants;
import ac.uk.york.typhon.analytics.commons.datatypes.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.topic.StreamManager;

public class EventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager
				.initializeSource(AnalyticTopicType.PRE, PreEvent.class);

		// dataStream.print(); //print the data stream as received

		dataStream = dataStream.map(new MapFunction<Event, Event>() {
			@Override
			public Event map(Event event) throws Exception {
				((PreEvent) event).setAuthenticated(Math.random() < 0.5);
				System.out.println(event);
				return event;
			}
		}).returns(Event.class);

		StreamManager.initializeSink(ExternalTopicType.AUTHORIZATION,
				dataStream);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE );

	}

}
