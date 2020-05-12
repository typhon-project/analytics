import ac.york.typhon.analytics.channel.ChannelBuilder;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class AnalyticsRunner {

	public static void main(String[] args) throws Exception {

		// Caller for the TestAnalyticScenario
		ChannelBuilder.build(new TestAnalyticScenario(), AnalyticTopicType.POST);
		
		// If you need to run other analytics scenarios then copy-paste the above line and replace the
		// "TestAnalyticScenario()" with the name of the class the contains the new analytic scenario
	}

}
