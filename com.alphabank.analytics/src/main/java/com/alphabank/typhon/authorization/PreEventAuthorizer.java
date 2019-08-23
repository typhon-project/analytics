package com.alphabank.typhon.authorization;

import java.util.ArrayList;
import java.util.List;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

import com.alphabank.typhon.authorization.filters.EventFilter;
import com.alphabank.typhon.authorization.filters.FinancialEventInsertFilter;
import com.alphabank.typhon.authorization.filters.GenericEventFilter;
import com.alphabank.typhon.authorization.filters.NonFinancialEventInsertFilter;
import com.alphabank.typhon.commons.AlphaEnum;

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

		EventFilter financialEventInsertFilter = new FinancialEventInsertFilter();
		EventFilter nonFinancialEventInsertFilter = new NonFinancialEventInsertFilter();
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
						if (financialEventInsertFilter.checkCondition(event)) {
							output.add(financialEventInsertFilter.getLabel());
						}
						if (nonFinancialEventInsertFilter.checkCondition(event)) {
							output.add(nonFinancialEventInsertFilter.getLabel());
						} else {
							output.add(genericEventFilter.getLabel());
						}
						return output;
					}
				});

		splitStream.print();

		DataStream<Event> filteredStream = splitStream.select(nonFinancialEventInsertFilter.getLabel());
		DataStream<Event> genericEvents = splitStream.select(genericEventFilter.getLabel());
		
		DataStream<Event> processedStream = nonFinancialEventInsertFilter.analyse(filteredStream);

		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				processedStream);
		StreamManager.initializeSink(AlphaEnum.AUTHORIZATION,
				genericEvents);

		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}
}
