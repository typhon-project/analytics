package finalreview.auth;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

public class DemoPreEventAuthorizer {

	public static void main(String[] args) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(AnalyticTopicType.PRE, PreEvent.class);
		
		OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
		
			EnableResultSet enableResultSet  = new EnableResultSet(); 
			OutputTag<Event> enableResultSetNotResponsibleOrApprovedTag = new OutputTag<Event>(enableResultSet.getLabel()) {};
		
		
	
				SingleOutputStreamOperator<Event> enableResultSetRejectedOrApprovedSplitStream = enableResultSet.run(dataStream, enableResultSet);
		
		
		DataStream<Event> finalStream = enableResultSetRejectedOrApprovedSplitStream.getSideOutput(enableResultSetNotResponsibleOrApprovedTag);
		
		DataStream<Event> finalRejectedStream = enableResultSetRejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag);
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
