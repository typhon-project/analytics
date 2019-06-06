package com.alphabank.typhon.authorization;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

public abstract class GenericAuthorisationTask {
	
	public abstract SplitStream<Event> run(DataStream<Event> preEvents);
	
	public abstract boolean checkCondition(Event event);
	
	public abstract boolean shouldIReject(Event event);

}
