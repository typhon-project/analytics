package analytics;

import java.util.UUID;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class DefaultAnalyticsRunner {

	public static void main(String[] args) throws Exception{

		AnalyticsJobBuilder.build(new DefaultAnalytics(), AnalyticTopicType.POST, UUID.randomUUID().toString());

	}

}
