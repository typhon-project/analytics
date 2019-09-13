package com.twicky.analytics.utilities.filters;

import org.apache.flink.api.common.functions.FilterFunction;

import com.twicky.dto.TweetDTO;

public class TrendingAccountsFilter implements FilterFunction<TweetDTO> {

	private final int RETWEETED_THRESHOLD = 1000;
	private final int FAVORITED_THRESHOLD = 1000;
	
	@Override
	public boolean filter(TweetDTO tweet) throws Exception {
		// TODO: Why are some values null? Find out...
		if (tweet.getFavoriteCount()== null || tweet.getRetweetCount()== null) {
			return false;
		} else if (Integer.parseInt(tweet.getFavoriteCount()) >= FAVORITED_THRESHOLD || Integer.parseInt(tweet.getRetweetCount()) >= RETWEETED_THRESHOLD) {
			return true;
		} else {
			return false;
		}
	}
}
