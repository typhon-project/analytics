package com.twicky.analytics.utilities.filters;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.flink.api.common.functions.FilterFunction;

import com.twicky.dto.TweetDTO;

public class NonMonitoredAccountsFilter implements FilterFunction<TweetDTO> {

	// FIXME: This is not the best approach. It should be better to query the DB
	// each time to get an up-to-date list of the users.
	private ArrayList<String> usersList = new ArrayList<String>(
			Arrays.asList("RemainerNow", "SoVeryBrexit", "astro_trader", "VermisstPfarrer", "LeaveHQ", "deni444",
					"Onmeed", "SomersetBlue", "oflynnsocial", "DalbidEU", "docuterror", "ShottsJimmie", "Liptoplap",
					"mamamuse", "Rachel5742", "JPLT59", "Steephen612", "Eyesheavenward", "PeteNorth303"));

	@Override
	public boolean filter(TweetDTO tweet) throws Exception {
		if (usersList.contains(tweet.getUserScreenName())) {
			return false;
		} else {
			return true;
		}
	}

}
