package ac.york.typhon.analytics.test.scenario1.authorization;

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

		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1549061265232435267L;
		};

		ValidateCustomers validateCustomers = new ValidateCustomers();
		OutputTag<Event> validateCustomersNotResponsibleOrApprovedTag = new OutputTag<Event>(
				validateCustomers.getLabel()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2144589595215470845L;
		};

		ValidateFeedbacks validateFeedbacks = new ValidateFeedbacks();
		OutputTag<Event> validateFeedbacksNotResponsibleOrApprovedTag = new OutputTag<Event>(
				validateFeedbacks.getLabel()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -7335664092140679371L;
		};

		SingleOutputStreamOperator<Event> validateCustomersRejectedOrApprovedSplitStream = validateCustomers
				.run(dataStream, validateCustomers);

		SingleOutputStreamOperator<Event> validateFeedbacksRejectedOrApprovedSplitStream = validateFeedbacks
				.run(validateCustomersRejectedOrApprovedSplitStream
						.getSideOutput(validateCustomersNotResponsibleOrApprovedTag), validateFeedbacks);
		DataStream<Event> finalStream = validateFeedbacksRejectedOrApprovedSplitStream
				.getSideOutput(validateFeedbacksNotResponsibleOrApprovedTag);

		DataStream<Event> finalRejectedStream = validateCustomersRejectedOrApprovedSplitStream
				.getSideOutput(rejectedOutputTag)
				.union(validateFeedbacksRejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag));

		StreamManager.initializeSink(AnalyticTopicType.AUTH, finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH, finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);

	}
}
