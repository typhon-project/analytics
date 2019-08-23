package com.twicky;

import com.twicky.analytics.RetweetsPerTweetAnalyzer;
import com.twicky.analytics.RetweetsPerUserAnalyzer;
import com.twicky.analytics.TweetsPerWindowAnalyzer;
import com.twicky.commons.TwickyTopics;

import ac.york.typhon.analytics.channel.ChannelBuilder;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class D_AnalyzerCaller {

	public static void main(String[] args) throws Exception {

		// Number of tweets over time
		ChannelBuilder.build(new TweetsPerWindowAnalyzer(),
				AnalyticTopicType.POST, TwickyTopics.TWICKY);

		// Number of retweets over time for each user
		// ChannelBuilder.build(new RetweetsPerUserAnalyzer(),
		// AnalyticTopicType.POST, PostEvent.class, TwickyTopics.TWICKY);

		// Number of retweets per tweet
		// ChannelBuilder.build(new RetweetsPerTweetAnalyzer(),
		// AnalyticTopicType.POST,
		// // PostEvent.class,
		// TwickyTopics.TWICKY);

		// TweetsOverTimeActivity.main(null);
		// RetweetsPerUserActivity.main(null);

	}

}
