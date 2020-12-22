package ac.york.typhon.analytics.generation.plugin;

public class DefaultAnalyticsProperties {

	public static String contents() {
		return "# configuration for topics url and ports\r\n" + 
				"\r\n" + 
				"# pre event topic\n" + 
				"topic.event.pre.group.id=pre_event_group\r\n" + 
				"topic.event.pre.bootstrap.servers=localhost:29092\r\n" + 
				"topic.event.pre.zookeeper.connect=localhost:2181\r\n" + 
				"topic.event.pre.auto.offset.reset=earliest\r\n" + 
				"topic.event.pre.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent\r\n" + 
				"\r\n" + 
				"# post event topic\r\n" + 
				"topic.event.post.group.id=post_event_group\r\n" + 
				"topic.event.post.bootstrap.servers=localhost:29092\r\n" + 
				"topic.event.post.zookeeper.connect=localhost:2181\r\n" + 
				"topic.event.post.auto.offset.reset=earliest\r\n" + 
				"topic.event.post.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PostEvent\r\n" + 
				"\r\n" + 
				"# authorization event topic\r\n" + 
				"topic.event.auth.group.id=auth_event_group\r\n" + 
				"topic.event.auth.bootstrap.servers=localhost:29092\r\n" + 
				"topic.event.auth.zookeeper.connect=localhost:2181\r\n" + 
				"topic.event.auth.auto.offset.reset=earliest\r\n" + 
				"topic.event.auth.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent";
	}
	
	public static String remoteContents() {
		return "# configuration for topics url and ports\r\n" + 
				"\r\n" + 
				"# pre event topic\n" + 
				"topic.event.pre.group.id=pre_event_group\r\n" + 
				"topic.event.pre.bootstrap.servers=kafka:9092\r\n" + 
				"topic.event.pre.zookeeper.connect=kafka:2181\r\n" + 
				"topic.event.pre.auto.offset.reset=earliest\r\n" + 
				"topic.event.pre.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent\r\n" + 
				"\r\n" + 
				"# post event topic\r\n" + 
				"topic.event.post.group.id=post_event_group\r\n" + 
				"topic.event.post.bootstrap.servers=kafka:9092\r\n" + 
				"topic.event.post.zookeeper.connect=kafka:2181\r\n" + 
				"topic.event.post.auto.offset.reset=earliest\r\n" + 
				"topic.event.post.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PostEvent\r\n" + 
				"\r\n" + 
				"# authorization event topic\r\n" + 
				"topic.event.auth.group.id=auth_event_group\r\n" + 
				"topic.event.auth.bootstrap.servers=kafka:9092\r\n" + 
				"topic.event.auth.zookeeper.connect=kafka:2181\r\n" + 
				"topic.event.auth.auto.offset.reset=earliest\r\n" + 
				"topic.event.auth.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent";
	}
	
	public static String remoteK8sContents() {
		return "# configuration for topics url and ports\r\n" + 
				"\r\n" + 
				"# pre event topic\n" + 
				"topic.event.pre.group.id=pre_event_group\r\n" + 
				"topic.event.pre.bootstrap.servers=typhon-cluster-kafka-bootstrap:9092\r\n" + 
				"topic.event.pre.zookeeper.connect=typhon-cluster-zookeeper-client.typhon:2181\r\n" + 
				"topic.event.pre.auto.offset.reset=earliest\r\n" + 
				"topic.event.pre.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent\r\n" + 
				"\r\n" + 
				"# post event topic\r\n" + 
				"topic.event.post.group.id=post_event_group\r\n" + 
				"topic.event.post.bootstrap.servers=typhon-cluster-kafka-bootstrap:9092\r\n" + 
				"topic.event.post.zookeeper.connect=typhon-cluster-zookeeper-client.typhon:2181\r\n" + 
				"topic.event.post.auto.offset.reset=earliest\r\n" + 
				"topic.event.post.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PostEvent\r\n" + 
				"\r\n" + 
				"# authorization event topic\r\n" + 
				"topic.event.auth.group.id=auth_event_group\r\n" + 
				"topic.event.auth.bootstrap.servers=typhon-cluster-kafka-bootstrap:9092\r\n" + 
				"topic.event.auth.zookeeper.connect=typhon-cluster-zookeeper-client.typhon:2181\r\n" + 
				"topic.event.auth.auto.offset.reset=earliest\r\n" + 
				"topic.event.auth.event.schema.class=ac.york.typhon.analytics.commons.datatypes.events.PreEvent";
	}
	
	
}
