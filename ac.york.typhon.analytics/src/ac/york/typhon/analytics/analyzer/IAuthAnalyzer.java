package ac.york.typhon.analytics.analyzer;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

public interface IAuthAnalyzer {

	/**
	 * 
	 */

	public abstract DataStream<Event> analyze(DataStream<Event> eventsStream)
			throws Exception;

	public default String getLabel() {
		return this.getClass().getName();
	}
}
