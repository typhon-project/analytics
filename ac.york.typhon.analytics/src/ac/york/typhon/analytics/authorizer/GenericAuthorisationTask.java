package ac.york.typhon.analytics.authorizer;

import java.io.Serializable;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;

public abstract class GenericAuthorisationTask implements Serializable {

	public SingleOutputStreamOperator<Event> run(DataStream<Event> preEvents, GenericAuthorisationTask task) {

		SingleOutputStreamOperator<Event> sideOutputStream = preEvents.process(new ProcessFunction<Event, Event>() {

			OutputTag<Event> rejectedOutputTag = new OutputTag<Event>("Rejected") {};
			OutputTag<Event> taskOutputTag = new OutputTag<Event>(task.getLabel()) {};

			@Override
			public void processElement(Event event, Context ctx, Collector<Event> out) throws Exception {
				// emit data to regular output
//				out.collect(event);
				((PreEvent) event).setAuthenticated(true);
				// If I am not responsible for such event, just put them into the topic named by
				// my name so the next AuthTask can consume it.
				if (!task.checkCondition(event)) {
//					System.out.println(task.getLabel() + " is not responsible for this event (" + event.getId() + ") "
//							+ event.getQuery());
					ctx.output(taskOutputTag, event);
					// Else (if I am responsible for such events) call my shouldIReject method. If
					// shouldIReject says reject, then put it in the rejected topic.
					// If it says accept then put it in the same topic as above (named by my name)
					// so the next filter can check it.
					// In other words, no matter what, unless an event is rejected, it always goes
					// to a topic with my name so the next auth task can consume it
				} else {
//					System.out.println(task.getLabel() + " is responsible for this event (" + event.getId() + ") "
//							+ event.getQuery());
					if (task.shouldIReject(event)) {
//						System.out.println(
//								task.getLabel() + " rejects this event (" + event.getId() + ") " + event.getQuery());
						((PreEvent) event).setAuthenticated(false);
						ctx.output(rejectedOutputTag, event);
					} else {
//						System.out.println(
//								task.getLabel() + " approves this event (" + event.getId() + ") " + event.getQuery());
//						((PreEvent) event).setAuthenticated(true);
						ctx.output(taskOutputTag, event);
					}
				}
			}
		});
		return sideOutputStream;
	}

	public abstract boolean checkCondition(Event event);

	public abstract boolean shouldIReject(Event event);

	public String getLabel() {
		return this.getClass().getName();
	}

}
