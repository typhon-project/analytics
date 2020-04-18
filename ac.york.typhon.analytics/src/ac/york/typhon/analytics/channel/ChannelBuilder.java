package ac.york.typhon.analytics.channel;

import org.apache.flink.streaming.api.datastream.DataStream;

import ac.york.typhon.analytics.analyzer.IAnalyzer;
import ac.york.typhon.analytics.analyzer.IAuthAnalyzer;
import ac.york.typhon.analytics.commons.AppConfiguration;
import ac.york.typhon.analytics.commons.Constants;
import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.enums.ITopicType;
import ac.york.typhon.analytics.streaming.StreamManager;

public class ChannelBuilder {

	public static void build(IAuthAnalyzer topicController,
			ITopicType sourceTopic,
			ITopicType destinationTopic) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				sourceTopic, Class.forName(AppConfiguration
						.getString(Constants.Properties.Topic.name(sourceTopic
								.getLabel()).EVENT_SCHEMA_CLASS)));

		dataStream = topicController.analyze(dataStream);

		StreamManager.initializeSink(destinationTopic, dataStream);

		StreamManager.startExecutionEnvironment(sourceTopic);

	}
	
	public static void build(IAnalyzer topicController,
			ITopicType sourceTopic) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				sourceTopic, Class.forName(AppConfiguration
						.getString(Constants.Properties.Topic.name(sourceTopic
								.getLabel()).EVENT_SCHEMA_CLASS)));
		topicController.analyze(dataStream);

		StreamManager.startExecutionEnvironment(sourceTopic);

	}
	
	public static void build(IAnalyzer topicController,
			ITopicType sourceTopic, String groupId) throws Exception {

		DataStream<Event> dataStream = StreamManager.initializeSource(
				sourceTopic, Class.forName(AppConfiguration
						.getString(Constants.Properties.Topic.name(sourceTopic
								.getLabel()).EVENT_SCHEMA_CLASS)), groupId);
		topicController.analyze(dataStream);

		StreamManager.startExecutionEnvironment(sourceTopic);

	}
}
