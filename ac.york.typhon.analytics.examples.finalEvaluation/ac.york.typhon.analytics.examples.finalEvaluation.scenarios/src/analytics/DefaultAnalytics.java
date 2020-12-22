package analytics;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class DefaultAnalytics implements IAnalyzer {
	
	public void analyze(DataStream<Event> eventsStream) throws Exception {

		eventsStream.print();
		
	}

}
