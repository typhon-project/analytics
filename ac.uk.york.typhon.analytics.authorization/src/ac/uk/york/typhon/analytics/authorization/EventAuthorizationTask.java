package ac.uk.york.typhon.analytics.authorization;

import java.io.Serializable;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public abstract class EventAuthorizationTask implements Serializable {
	
	public abstract boolean checkCondition(Event preEvent);

	public abstract Event analyse(Event event) throws Exception;

	public DataStream<Event> analyse(DataStream<Event> splittedStream) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
