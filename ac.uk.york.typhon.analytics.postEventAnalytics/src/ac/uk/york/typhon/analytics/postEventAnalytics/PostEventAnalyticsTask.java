package ac.uk.york.typhon.analytics.postEventAnalytics;

import java.io.Serializable;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;


public abstract class PostEventAnalyticsTask implements Serializable {
	
	public abstract DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception;

}
