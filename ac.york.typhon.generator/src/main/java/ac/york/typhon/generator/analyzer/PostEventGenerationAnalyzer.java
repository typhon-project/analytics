package ac.york.typhon.generator.analyzer;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.generator.helper.Utils;

public class PostEventGenerationAnalyzer implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception {

		eventsStream = eventsStream.map(new MapFunction<Event, Event>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Event map(Event event) throws Exception {
				PreEvent preEvent = (PreEvent) event;
				Event postEvent = null;
				if (preEvent.isAuthenticated()) {

					// DMLCommand dmlCommand = StatementFactory
					// .initializeDML(preEvent.getQuery());

					postEvent = new PostEvent(event.getId(), event.getQuery(),
							new Boolean(true), preEvent.getQueryTime(), Utils
									.generateTimeStamp(), preEvent);

				}

				return postEvent;
			}
		}).returns(Event.class);
		return eventsStream;
	}

}
