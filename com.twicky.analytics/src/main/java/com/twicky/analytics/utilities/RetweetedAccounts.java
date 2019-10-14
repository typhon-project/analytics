package com.twicky.analytics.utilities;

import java.util.HashMap;
import java.util.HashSet;

public class RetweetedAccounts {
	HashMap<String, HashSet<String>> retweetedAccounts = new HashMap<String, HashSet<String>>();

	public HashMap<String, HashSet<String>> getRetweetedAccounts() {
		return retweetedAccounts;
	}

	public void setRetweetedAccounts(HashMap<String, HashSet<String>> retweetedAccounts) {
		this.retweetedAccounts = retweetedAccounts;
	}
}
