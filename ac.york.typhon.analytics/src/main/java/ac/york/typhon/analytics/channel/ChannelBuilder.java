package ac.york.typhon.analytics.channel;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.enums.ITopicType;
import ac.york.typhon.analytics.messaging.StreamManager;
import ac.york.typhon.analytics.process.StreamAnalyzer;

public class ChannelBuilder {

	public static void build(StreamAnalyzer streamAnalyzer,
			ITopicType sourceTopic, Class<?> sourceClass,
			ITopicType destinationTopic) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				sourceTopic, sourceClass);

		dataStream = streamAnalyzer.analyse(dataStream);

		StreamManager.initializeSink(destinationTopic, dataStream);

		StreamManager.startExecutionEnvironment(sourceTopic);

	}

}
