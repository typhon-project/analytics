import ac.york.typhon.analytics.channel.ChannelBuilder;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class CallerTwo {

	public static void main(String[] args) throws Exception {
		ChannelBuilder.build(new AnalyticsScenarioTwo(), AnalyticTopicType.POST);
	}
}
