package ecommerceAuth;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

public class PreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
			ValidateUsers validateUsers  = new ValidateUsers(); 
			OutputTag<Event> validateUsersNotResponsibleOrApprovedTag = new OutputTag<Event>(validateUsers.getLabel()) {};
		
			ValidateReviews validateReviews  = new ValidateReviews(); 
			OutputTag<Event> validateReviewsNotResponsibleOrApprovedTag = new OutputTag<Event>(validateReviews.getLabel()) {};
		
		
	
				SingleOutputStreamOperator<Event> validateUsersRejectedOrApprovedSplitStream = validateUsers.run(dataStream, validateUsers);
		
		
				SingleOutputStreamOperator<Event> validateReviewsRejectedOrApprovedSplitStream = validateReviews.run(validateUsersRejectedOrApprovedSplitStream.getSideOutput(validateUsersNotResponsibleOrApprovedTag), validateReviews);
		
		DataStream<Event> finalStream = validateReviewsRejectedOrApprovedSplitStream.getSideOutput(validateReviewsNotResponsibleOrApprovedTag);

		
		DataStream<Event> finalRejectedStream = validateUsersRejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag).union(validateReviewsRejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
