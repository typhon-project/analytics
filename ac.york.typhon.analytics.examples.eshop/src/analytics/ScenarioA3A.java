package analytics;

import java.util.ArrayList;import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import engineering.swat.typhonql.ast.KeyVal;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class ScenarioA3A implements IAnalyzer {

	@Override
	public DataStream<Event> analyze(DataStream<Event> eventsStream) throws Exception {
		DataStream<Tuple2<String, String>> reviewsStream = eventsStream.map(new MapFunction<Event, PostEvent>() {

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
		.map(new MapFunction<PostEvent, Tuple2<String, String>>() {

			@Override
			public Tuple2<String, String> map(PostEvent arg0) throws Exception {
				String query = arg0.getQuery();
				String productId = query.split("product: \\[\\#")[1].split("]")[0];
				String userId = query.split("user: \\[\\#")[1].split("]")[0];
				Tuple2<String, String> result = new Tuple2<String, String>();
				result.f0 = productId;
				result.f1 = userId;
				return result;
			}
		});
		reviewsStream.print();
				
		return eventsStream;
	}

}
