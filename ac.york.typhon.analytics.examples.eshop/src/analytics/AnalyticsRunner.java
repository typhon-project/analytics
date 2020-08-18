package analytics;

import java.util.UUID;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import commons.EShopTopics;

public class AnalyticsRunner {

	public static void main(String[] args) throws Exception {
		AnalyticsJobBuilder.build(new TestScenario2(), AnalyticTopicType.POST, UUID.randomUUID().toString());
	}

}
