package analytics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class UserSpammingRunner {
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");		
		System.out.println("Main started At: " + sdf.format(new Date()));
		AnalyticsJobBuilder.build(new UserSpammingScenario(), AnalyticTopicType.POST, UUID.randomUUID().toString());
//		AnalyticsJobBuilder.build(new UserSpammingScenarioTesting(), AnalyticTopicType.POST, UUID.randomUUID().toString());

	}

}
