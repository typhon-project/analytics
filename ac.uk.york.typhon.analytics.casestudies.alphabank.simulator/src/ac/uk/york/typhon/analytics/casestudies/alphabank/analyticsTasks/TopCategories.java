package ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;
import ac.uk.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.uk.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.uk.york.typhon.analytics.messaging.StreamManager;
import ac.uk.york.typhon.analytics.postEventAnalytics.PostEventAnalyticsTask;

public class TopCategories extends PostEventAnalyticsTask {
	
	

	@Override
	public DataStream<Event> analyse(DataStream<Event> postEvents) throws Exception {
		DataStream<Event> results = postEvents
		.filter(new FilterFunction<Event>() {
			
			@Override
			public boolean filter(Event arg0) throws Exception {
				if (arg0.getQuery().toLowerCase().contains("insert into table fnc_ev")) {
					return true;
				}
				return false;
			}
		})
		.map(new MapFunction<Event, Event>() {

			@Override
			public Event map(Event arg0) throws Exception {
				System.out.println(arg0.getQuery());
				return arg0;
			}
		});
		return results;
	}

}
