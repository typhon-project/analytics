package auth;

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
		
			Task1 task1  = new Task1(); 
			OutputTag<Event> task1NotResponsibleOrApprovedTag = new OutputTag<Event>(task1.getLabel()) {};
			OutputTag<Event> task1ResponsibleTag = new OutputTag<Event>(task1.getLabel()+"Responsible") {};
		
			Task2 task2  = new Task2(); 
			OutputTag<Event> task2NotResponsibleOrApprovedTag = new OutputTag<Event>(task2.getLabel()) {};
		
			Task3 task3  = new Task3(); 
			OutputTag<Event> task3NotResponsibleOrApprovedTag = new OutputTag<Event>(task3.getLabel()) {};
			OutputTag<Event> task3ResponsibleTag = new OutputTag<Event>(task3.getLabel()+"Responsible") {};
		
		
	
				SingleOutputStreamOperator<Event> task1ResponsibleOrNotResponsibleSplitStream = task1.run(dataStream, task1);
				DataStream<Event> task1ResponsibleStream = task1ResponsibleOrNotResponsibleSplitStream.getSideOutput(task1ResponsibleTag);
				DataStream<Event> task1RejectedAndApprovedStream = task1.shouldIRejectAsStream(task1ResponsibleStream);
				SingleOutputStreamOperator<Event> task1RejectedOrApprovedSplitStream = task1.moveToRejected(task1RejectedAndApprovedStream,task1);
		
		
				SingleOutputStreamOperator<Event> task2RejectedOrApprovedSplitStream = task2.run(task1RejectedOrApprovedSplitStream.getSideOutput(task1NotResponsibleOrApprovedTag), task2);
				SingleOutputStreamOperator<Event> task3ResponsibleOrNotResponsibleSplitStream = task3.run(task2RejectedOrApprovedSplitStream.getSideOutput(task2NotResponsibleOrApprovedTag), task3);
				DataStream<Event> task3ResponsibleStream = task3ResponsibleOrNotResponsibleSplitStream.getSideOutput(task3ResponsibleTag);
				DataStream<Event> task3RejectedAndApprovedStream = task3.shouldIRejectAsStream(task3ResponsibleStream);
				SingleOutputStreamOperator<Event> task3RejectedOrApprovedSplitStream = task3.moveToRejected(task3RejectedAndApprovedStream,task3);
		
		DataStream<Event> finalStream = task3RejectedOrApprovedSplitStream.getSideOutput(task3NotResponsibleOrApprovedTag);

		
		DataStream<Event> finalRejectedStream = task1RejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag).union(task2RejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag)).union(task3RejectedOrApprovedSplitStream.getSideOutput(rejectedOutputTag));
		
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalStream);
		StreamManager.initializeSink(AnalyticTopicType.AUTH,
				finalRejectedStream);
		StreamManager.startExecutionEnvironment(AnalyticTopicType.PRE);
		

	}
}
