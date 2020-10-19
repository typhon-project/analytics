package ac.york.typhon.analytics.generation.plugin;

public class DefaultAnalytics {

	public static String contents() {
		return "package analytics;\r\n" + 
				"\r\n" + 
				"import org.apache.flink.streaming.api.datastream.DataStream;\r\n" + 
				"\r\n" + 
				"import ac.york.typhon.analytics.analyzer.IAnalyzer;\r\n" + 
				"import ac.york.typhon.analytics.commons.datatypes.events.Event;\r\n" + 
				"\r\n" + 
				"public class DefaultAnalytics implements IAnalyzer {\r\n" + 
				"	\r\n" + 
				"	public void analyze(DataStream<Event> eventsStream) throws Exception {\r\n" + 
				"\r\n" + 
				"		eventsStream.print();\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
	}
	
}
