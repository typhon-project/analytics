package ac.york.typhon.analytics.process;

import java.io.Serializable;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;

public abstract class StreamAnalyzer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract DataStream<Event> analyse(DataStream<Event> eventsStream)
			throws Exception;

	public String getLabel() {
		return this.getClass().getName();
	}
}
