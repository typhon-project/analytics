import ac.york.typhon.analytics.channel.ChannelBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class RunnerClassThatCallsTheExample {

	public static void main(String[] args) throws Exception {
		// Caller for the AnalyticScenario
		ChannelBuilder.build(new HowToExampleClassWithSomeLogic(), AnalyticTopicType.POST);

		// If you need to run other analytics scenarios then copy-paste the above line and replace the
		// "TestAnalyticScenario()" with the name of the class the contains the new analytic scenario
	}
}
