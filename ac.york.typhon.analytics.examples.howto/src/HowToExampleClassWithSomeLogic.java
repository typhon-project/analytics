import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class HowToExampleClassWithSomeLogic implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		
		eventsStream.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event arg0) throws Exception {
				// Cast Event to PostEvent.
				PostEvent postEvent = (PostEvent) arg0;
				// Filter events and get only those that are insert statements of CreditCard entities in the eshop example.
				if (postEvent.getQuery().contains("insert CreditCard")) {
					return true;
				}
				return false;
			}
		})
		// Create a Tuple credit card number and credit card expiry year
		.map(new MapFunction<Event, Tuple2<String,String>>() {

			@Override
			public Tuple2<String,String> map(Event arg0) throws Exception {
				PostEvent postEvent = (PostEvent) arg0;
				// Get the number of the card from the query
				String number = postEvent.getQuery().split("number: \"")[1].split("\", expiryDate:")[0];
				// Get the expiry date and then the year from the query
				String expiryDate = postEvent.getQuery().split("expiryDate: \"")[1].split("\"}")[0];
				String expiryYear = expiryDate.substring(expiryDate.length()-4, expiryDate.length());
				// Create the tuple and pass it on
				return new Tuple2(number, expiryYear);
			}
		})
		// Filter those that has expired since the previous year
		.filter(new FilterFunction<Tuple2<String, String>>() {

			@Override
			public boolean filter(Tuple2<String, String> arg0) throws Exception {
				// Get the year from the tuple (f0 is the first entry, f1 is the second entry in a tuple, etc.
				int expiryYear = Integer.parseInt(arg0.f1);
				if (expiryYear <= 2019) {
					return true;
				}
				return false;
			}
			
		})
		// Print the tuples number and expiry year. That's why we carried the number as well in the tuple.
		.print();
	}

}
