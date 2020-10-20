package ac.york.typhon.analytics.generation.plugin;

public class DefaultAnalyticsRunner {

	public static String contents() {
		return "package analytics;\r\n" + 
				"\r\n" + 
				"import java.util.UUID;\r\n" + 
				"\r\n" + 
				"import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;\r\n" + 
				"import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;\r\n" + 
				"\r\n" + 
				"public class DefaultAnalyticsRunner {\r\n" + 
				"\r\n" + 
				"	public static void main(String[] args) throws Exception{\r\n" + 
				"\r\n" + 
				"		AnalyticsJobBuilder.build(new DefaultAnalytics(), AnalyticTopicType.POST, UUID.randomUUID().toString());\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
	}
	
}
