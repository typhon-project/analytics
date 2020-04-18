package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class UndecisiveScenario implements IAnalyzer {

	@Override
	public void analyze(DataStream<Event> eventsStream) throws Exception {
		DataStream<String> deletedBasketProductIds = eventsStream.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		}).filter(new FilterFunction<PostEvent>() {

			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("delete BasketProduct");
			}
		})
		.map(new MapFunction<PostEvent,String>() {

			@Override
			public String map(PostEvent arg0) throws Exception {
				String deletedBasketId = arg0.getQuery().split("@id == ")[1];
				return deletedBasketId;
			}
		});
		
		DataStream<Tuple2<String, String>> insertedBasketProductIds = eventsStream.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		}).filter(new FilterFunction<PostEvent>() {

			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("insert BasketProduct");
			}
		})
		.map(new MapFunction<PostEvent, Tuple2<String, String>>() {

			@Override
			public Tuple2<String, String> map(PostEvent arg0) throws Exception {
				// This is aa good example why inverted selects are needed. By havin the info in 
				// the delete BasketProduct (I get the basketId value before deletion so I can just some 
				// all the delete events on this basket id and then I can just use a stream join to match 
				// user ids with basket ids or simply query the DB.
				String basketProductId = "I DON'T HAVE THIS!";
				String basketId = arg0.getQuery().split("basket: [#")[1];
				Tuple2<String, String> tuple = new Tuple2<String, String>(basketProductId, basketId);
				return tuple;
			}
		});
	}

}
