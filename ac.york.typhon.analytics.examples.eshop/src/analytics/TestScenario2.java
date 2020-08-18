package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.Address;
import ac.york.typhon.analytics.commons.datatypes.commands.InsertCommand;
import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class TestScenario2 implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		eventsStream
		.map(new MapFunction<Event, Address>() {

			@Override
			public Address map(Event value) throws Exception {
				DeserializedPostEvent postEvent = (DeserializedPostEvent) value;
				InsertCommand ic = (InsertCommand) postEvent.getCommands().get(0);
				Address ad = (Address) ic.getInsertedEntities().get(0);
				ad.getStreet();
				return ad;
			}
			
		})
		.print();
	}

}
