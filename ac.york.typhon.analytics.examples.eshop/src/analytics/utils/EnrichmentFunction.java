package analytics.utils;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import datatypes.OrderedProduct;
import datatypes.Review;

public class EnrichmentFunction extends RichCoFlatMapFunction<OrderedProduct, Review, Tuple2<OrderedProduct, Review>> {

		private ValueState<OrderedProduct> opState;
		private ValueState<Review> reviewState;
		
		@Override
		public void open(Configuration config) throws Exception {
			opState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("op", OrderedProduct.class));
			reviewState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("review", Review.class));
		}

		@Override
		public void flatMap1(OrderedProduct op, Collector<Tuple2<OrderedProduct, Review>> out) throws Exception {
			if (reviewState.value() == null) {
				opState.update(op);
			} else {
				out.collect(new Tuple2<OrderedProduct, Review>(op, reviewState.value()));
				reviewState.clear();
			}
		}

		@Override
		public void flatMap2(Review review, Collector<Tuple2<OrderedProduct, Review>> out) throws Exception {
			if (opState.value() == null) {
				reviewState.update(review);
			} else {
				out.collect(new Tuple2<OrderedProduct, Review>(opState.value(), review));
				opState.clear();
			}
		}
	}