package analytics;

import java.util.UUID;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class UserSpammingRunner {
	
	public static void main(String[] args) throws Exception {
		AnalyticsJobBuilder.build(new UserSpammingScenario(), AnalyticTopicType.POST, UUID.randomUUID().toString());
//		AnalyticsJobBuilder.build(new UserSpammingScenarioTesting(), AnalyticTopicType.POST, UUID.randomUUID().toString());

	}

}
