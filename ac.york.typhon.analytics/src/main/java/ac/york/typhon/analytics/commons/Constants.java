package ac.york.typhon.analytics.commons;

public class Constants {
	public static final String PROPERTIES_FILENAME = "config.properties";

	public static interface Properties {

		public static final class Topic {
			private final static Topic topic = new Topic();
			private final static String TOPIC_EVENT = "topic.event.";

			public String GROUP_ID;
			public String BOOTSTRAP_SERVERS;
			public String ZOOKEEPER_CONNECT;
			public String AUTO_OFFSET_RESET;

			private static void reEvaluate(String topicName) {

				topic.GROUP_ID = TOPIC_EVENT + topicName + "." + "group.id";
				topic.BOOTSTRAP_SERVERS = TOPIC_EVENT + topicName + "."
						+ "bootstrap.servers";
				topic.ZOOKEEPER_CONNECT = TOPIC_EVENT + topicName + "."
						+ "host.url.port";

				topic.AUTO_OFFSET_RESET = TOPIC_EVENT + topicName + "."
						+ "auto.offset.reset";
			}

			public static Topic name(String topicName) {
				reEvaluate(topicName);
				return topic;
			}

		}

	}

}
// public static interface TopicName {
// public final String PRE = "pre";
// public final String POST = "post";
// public final String AUTHORIZATION = "authorization";
//
// }
// public interface ITopicType {
// String getLabel();
// }

// public enum TopicName {
// PRE("pre"), POST("post"), AUTHORIZATION("authorization");
// private String name;
//
// TopicName(String nameStr) {
// this.name = nameStr;
// }
//
// public String toString() {
// return this.name;
// }
// }
//