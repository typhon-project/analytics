package com.twicky;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;
import ac.york.typhon.generator.analyzer.PostEventGenerationAnalyzer;
import ac.york.typhon.generator.enums.QueryLanguageTopicType;

public class C_PostEventGeneratorCaller {

	public static void main(String[] args) throws Exception {
		// PostEventsGenerator.main(null);

		AnalyticsJobBuilder.build(new PostEventGenerationAnalyzer(),
				QueryLanguageTopicType.AUTHORIZATION, AnalyticTopicType.POST);

	}

}