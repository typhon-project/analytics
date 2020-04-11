package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class UndecisiveScenario implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {
		DataStream<String> deletedBPIds = eventsStream
		.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		})
		.filter(new FilterFunction<PostEvent>(
				) {
			
			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("delete BasketProduct");
			}
		})
		.map(new MapFunction<PostEvent, String>() {

			@Override
			public String map(PostEvent arg0) throws Exception {
				String deletedBasketProductId = arg0.getQuery().split("@id == ")[1];
				return deletedBasketProductId;
			}
		});
		
		DataStream<String> insertedBPIds = eventsStream
				.map(new MapFunction<Event, PostEvent>() {

					@Override
					public PostEvent map(Event arg0) throws Exception {
						return (PostEvent) arg0;
					}
				})
				.filter(new FilterFunction<PostEvent>(
						) {
					
					@Override
					public boolean filter(PostEvent pe) throws Exception {
						return pe.getQuery().contains("insert BasketProduct");
					}
				})
				.map(new MapFunction<PostEvent, String>() {

					@Override
					public String map(PostEvent arg0) throws Exception {
						String insertedBasketProductId = arg0.getQuery().split("@id == ")[1];
						return insertedBasketProductId;
					}
				});
		return eventsStream;
	}

}
