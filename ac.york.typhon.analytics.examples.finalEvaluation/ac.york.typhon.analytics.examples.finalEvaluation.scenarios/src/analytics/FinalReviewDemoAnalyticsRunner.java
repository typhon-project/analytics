package analytics;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.analytics.examples.finalEvaluation.demo.DemoUI;

public class FinalReviewDemoAnalyticsRunner {

	public static DemoUI ui = new DemoUI();

	public static void main(String[] args) throws Exception {

		FinalReviewDemoAnalytics da = new FinalReviewDemoAnalytics();

		ui.createUI();

		AnalyticsJobBuilder.build(da, AnalyticTopicType.POST, "1234567891");

//		Node n = graph.addNode("Customer1");
//		n.setAttribute("ui.label", "Customer1");
//		n = graph.addNode("B");
//		n.setAttribute("ui.label", "B");
//		n = graph.addNode("C");
//		n.setAttribute("ui.label", "C");
//		Edge e = graph.addEdge("AB", "Customer1", "B");
//		e.setAttribute("ui.label", "AB");
//		e = graph.addEdge("BC", "B", "C");
//		e.setAttribute("ui.label", "BC");
//		e = graph.addEdge("CA", "C", "Customer1");
//		e.setAttribute("ui.label", "CA");
		
	}

}
