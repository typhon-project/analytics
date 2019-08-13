package com.twicky.authorization;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.messaging.StreamManager;

import com.twicky.authorization.filters.EventFilter;
import com.twicky.authorization.filters.GenericEventFilter;
import com.twicky.authorization.filters.TwickyAllowNonBlankQueryFilter;
import com.twicky.commons.TwickyTopics;

//import com.alphabank.typhon.authorization.filters.EventFilter;
//import com.alphabank.typhon.authorization.filters.FinancialEventInsertFilter;
//import com.alphabank.typhon.authorization.filters.GenericEventFilter;
//import com.alphabank.typhon.authorization.filters.NonFinancialEventInsertFilter;
//import com.alphabank.typhon.commons.AlphaEnum;

/**
 * Checks for dormant account activity
 * 
 * @author Admin
 *
 */
public class PreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		// ExtractorManager.registerParsedStatements();

		DataStream<Event> dataStream = StreamManager.initializeSource(
				AnalyticTopicType.PRE, PreEvent.class);

		EventFilter nonBlankQueryFilter = new TwickyAllowNonBlankQueryFilter();
		EventFilter genericEventFilter = new GenericEventFilter();

		SplitStream<Event> splitStream = dataStream
				.split(new OutputSelector<Event>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Iterable<String> select(Event event) {
						List<String> output = new ArrayList<String>();
						if (nonBlankQueryFilter.checkCondition(event)) {
							output.add(nonBlankQueryFilter.getLabel());
						} else {
							output.add(genericEventFilter.getLabel());
						}
						return output;
					}
				});

		splitStream.print();

		DataStream<Event> filteredStream = splitStream.select(nonBlankQueryFilter.getLabel());
		DataStream<Event> genericEvents = splitStream.select(genericEventFilter.getLabel());

		DataStream<Event> processedStream = nonBlankQueryFilter.analyse(filteredStream);

		StreamManager.initializeSink(TwickyTopics.AUTHORIZATION, processedStream);
		StreamManager.initializeSink(TwickyTopics.AUTHORIZATION, genericEvents);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}
}
