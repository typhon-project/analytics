package analytics;

import java.util.Properties;
import java.util.UUID;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;

public class TestScenario implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {
		eventsStream.print();
		return eventsStream;
	}

}
