import java.util.Random;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class CallerOne {

	public static void main(String[] args) throws Exception {
		Random r = new Random();
		AnalyticsJobBuilder.build(new AnalyticsScenarioOne(), AnalyticTopicType.POST, String.valueOf(r.nextFloat()));
	}
}
