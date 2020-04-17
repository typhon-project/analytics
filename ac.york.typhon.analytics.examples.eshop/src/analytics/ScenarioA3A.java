package analytics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import datatypes.OrderedProduct;
import datatypes.Review;

public class ScenarioA3A implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {
		DataStream<Review> reviewsStream = eventsStream.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		})
		.filter(new FilterFunction<PostEvent>() {

			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("insert Review");
			}
		})
		.map(new MapFunction<PostEvent, Review>() {

			@Override
			public Review map(PostEvent arg0) throws Exception {
				String query = arg0.getQuery();
				String productId = query.split("product: \\[\\#")[1].split("]")[0];
				String userId = query.split("user: \\[\\#")[1].split("]")[0];
				Review review = new Review();
				review.setProduct(productId);
				review.setUser(userId);
				return review;
			}
		});
		
		DataStream<OrderedProduct> orderedProductStream = eventsStream.map(new MapFunction<Event, PostEvent>() {

			@Override
			public PostEvent map(Event arg0) throws Exception {
				return (PostEvent) arg0;
			}
		})
		.filter(new FilterFunction<PostEvent>() {

			@Override
			public boolean filter(PostEvent pe) throws Exception {
				return pe.getQuery().contains("insert OrderedProduct");
			}
		})
		.map(new MapFunction<PostEvent, OrderedProduct>() {

			@Override
			public OrderedProduct map(PostEvent arg0) throws Exception {
				String query = arg0.getQuery();
				String productId = query.split("product: \\[\\#")[1].split("]")[0];
				String userId = query.split("users: \\[\\#")[1].split("]")[0];
				OrderedProduct op = new OrderedProduct();
				op.setProduct(productId);
				op.setUser(userId);
				return op;
			}
		});
		orderedProductStream.print();	
		return eventsStream;
	}

}
