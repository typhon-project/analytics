package analytics;

import ac.york.typhon.analytics.channel.ChannelBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import commons.EShopTopics;

public class AnalyticsRunner {

	public static void main(String[] args) throws Exception {
		ChannelBuilder.build(new TestScenario(), AnalyticTopicType.POST, EShopTopics.ESHOPRESULTS);
	}

}
