package com.twicky;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

import com.twicky.analytics.AuthorizationAnalyzer;
import com.twicky.commons.TwickyTopics;

public class B_PreEventAuthorizerCaller {
	public static void main(String[] args) throws Exception {
		
		// PreEventAuthorizer.main(null);

		AnalyticsJobBuilder.build(new AuthorizationAnalyzer(),
				AnalyticTopicType.PRE, TwickyTopics.AUTHORIZATION);

	}

}
