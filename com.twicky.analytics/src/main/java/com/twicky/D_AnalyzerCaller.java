package com.twicky;

import com.twicky.analytics.SimilarAccountsAnalyzer;
import com.twicky.analytics.FavoritesPerTweetAnalyzer;
import com.twicky.analytics.FollowersOverTimeAnalyzer;
import com.twicky.analytics.MostPopularDayAnalyzer;
import com.twicky.analytics.MostPopularTimeAnalyzer;
import com.twicky.analytics.NonMonitoredWithSubstantialActivityAnalyzer;
import com.twicky.analytics.RetweetsPerTweetAnalyzer;
import com.twicky.analytics.RetweetsPerUserAnalyzer;
import com.twicky.analytics.TweetsOverTimeAnalyzer;
import com.twicky.analytics.TweetsPerWindowAnalyzer;
import com.twicky.commons.TwickyTopics;

import ac.york.typhon.analytics.builder.AnalyticsJobBuilder;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.enums.AnalyticTopicType;

public class D_AnalyzerCaller {

	public static void main(String[] args) throws Exception {

		// Number of tweets over Time
//		ChannelBuilder.build(new TweetsPerWindowAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);

		// Number of Retweets over Time for each User
//		ChannelBuilder.build(new RetweetsPerUserAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);

		// Number of Retweets per Tweet
//		ChannelBuilder.build(new RetweetsPerTweetAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
		
		// Number of Favorites per Tweet
//		ChannelBuilder.build(new FavoritesPerTweetAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
		
		// Number of Retweets per User
//		ChannelBuilder.build(new RetweetsPerUserAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
		
		//Number of Followers over Time
//		ChannelBuilder.build(new FollowersOverTimeAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);

		//Most Popular Hour
//		ChannelBuilder.build(new MostPopularTimeAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
				
		//Most Popular Day
//		ChannelBuilder.build(new MostPopularDayAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
		
		//Similar Accounts
		AnalyticsJobBuilder.build(new SimilarAccountsAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
		
		//Non Monitored Accounts
//		ChannelBuilder.build(new NonMonitoredWithSubstantialActivityAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);

		//Tweets Over Time (Thanos)
//		ChannelBuilder.build(new TweetsOverTimeAnalyzer(), AnalyticTopicType.POST, TwickyTopics.TWICKY);
	}

}
