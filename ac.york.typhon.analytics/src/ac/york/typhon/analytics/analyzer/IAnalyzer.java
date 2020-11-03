package ac.york.typhon.analytics.analyzer;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

public interface IAnalyzer {

	public abstract void analyze(DataStream<Event> eventsStream) throws Exception;

	public default void wrappedAnalyze(DataStream<Event> eventsStream) {
		try {
			analyze(eventsStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public default String getLabel() {
		return this.getClass().getName();
	}
}
