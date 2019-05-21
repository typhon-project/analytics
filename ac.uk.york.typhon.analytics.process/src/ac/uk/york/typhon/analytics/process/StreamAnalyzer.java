package ac.uk.york.typhon.analytics.process;

import java.io.Serializable;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;

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
