package com.twicky.commons;

import ac.york.typhon.analytics.commons.Constants;

public abstract class TwickyConstants extends Constants {

	public interface Table {

		public interface BlockedUser {
			public static String getName() {
				return BlockedUser.class.getName();
			}

			public static String ID = "id";
			public static String SCREEN_NAME = "screen_name";

		}

		public interface Query {
			public static String getName() {
				return Query.class.getName();
			}

			public static String ID = "id";
			public static String QUERY = "query";
			public static String TIMESTAMP = "timestamp";

		}

		public interface Tweet {
			public static String getName() {
				return Tweet.class.getName();
			}

			String BLOCKED = "blocked";
			String CREATED_AT = "created_at";
			String DISCOVERED_AT = "discovered_at";
			String DISCOVERER_SCREEN_NAME = "discoverer_screen_name";
			String FAVORITE_COUNT = "favorite_count";
			String GREEK = "greek";
			String ID = "id";
			String JSON = "json";
			String RETWEET_COUNT = "retweet_count";
			String TEXT = "text";
			String USER_SCREEN_NAME = "user_screen_name";

		}

		public interface User {

			public static String getName() {
				return User.class.getName();
			}

			public static String ID = "id";
			public static String LABEL = "label";
			public static String LAST_CHECKED = "last_checked";
			public static String SCREEN_NAME = "screen_name";

		}

	}

	public interface TwickyJson {

		String CREATED_AT = "created_at";
		String FAVORITE_COUNT = "favorite_count";
		String ID = "id";
		String RETWEET_COUNT = "retweet_count";
		String FULL_TEXT = "full_text";
		String USER = "user";
		String USER_ID = "id";
		String FOLLOWERS_COUNT = "followers_count";
		String SCREEN_NAME = "screen_name";
		String QUOTED_STATUS = "quoted_status";

	}

}
