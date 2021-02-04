package analytics;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.examples.finalEvaluation.demo.DemoUI;

public class FinalReviewDemoAnalyticsRunner {

	public static DemoUI ui = new DemoUI();

	public static void main(String[] args) throws Exception {

		FinalReviewDemoAnalytics da = new FinalReviewDemoAnalytics();

		ui.createUI();

		// specific kafka id so we only see new messages every time we re-load the demo
		// so no need to keep clearing the kafka queues
		AnalyticsJobBuilder.build(da, AnalyticTopicType.POST, "1234567891");

	}

}
