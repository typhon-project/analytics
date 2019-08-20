package com.twicky.extractors.update.extractor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import net.sf.jsqlparser.JSQLParserException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twicky.commons.TwickyConstants;
import com.twicky.extractors.update.UpdateExtractor;

public class TweetUpdateExtractor extends UpdateExtractor {

	private static final String INVALID_CHARACTERS = "?";
	private HashMap<String, String> fieldValueMap;
	private JsonNode jsonNode;

	public TweetUpdateExtractor(String query) throws JSQLParserException {

		super(query);
		this.fieldValueMap = populateFieldValueMap();

		// remove question marks in prepared statements
		for (Entry<String, String> entry : this.fieldValueMap.entrySet()) {
			if (StringUtils.containsOnly(entry.getValue(), INVALID_CHARACTERS)) {
				entry.setValue(null);
			}
		}

		String escapedJsonString = fieldValueMap
				.get(TwickyConstants.Table.Tweet.JSON);
		if (StringUtils.isNoneBlank(escapedJsonString)) {

			try {

				String unescapedJsonString = StringEscapeUtils
						.unescapeJson(escapedJsonString);
				ObjectMapper objectMapper = new ObjectMapper();
				jsonNode = objectMapper.readValue(unescapedJsonString,
						JsonNode.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public String getBlocked() {
		return fieldValueMap.get(TwickyConstants.Table.Tweet.BLOCKED);

	}

	public String getCreatedAt() {
		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.CREATED_AT);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {
			field = jsonNode.path(TwickyConstants.TwickyJson.CREATED_AT)
					.asText();

		}
		return field;

	}

	public String getDiscoveredAt() {
		// return fieldValueMap.get(TwickyConstants.Table.Tweet.DISCOVERED_AT);

		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.DISCOVERED_AT);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {
			field = jsonNode.path(TwickyConstants.TwickyJson.QUOTED_STATUS)
					.path(TwickyConstants.TwickyJson.CREATED_AT).asText();
		}
		return field;
	}

	public String getDiscovererScreenName() {
		// return fieldValueMap
		// .get(TwickyConstants.Table.Tweet.DISCOVERER_SCREEN_NAME);

		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.DISCOVERER_SCREEN_NAME);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {

			field = jsonNode.path(TwickyConstants.TwickyJson.QUOTED_STATUS)
					.path(TwickyConstants.TwickyJson.USER)
					.path(TwickyConstants.TwickyJson.SCREEN_NAME).asText();
		}
		return field;

	}

	public String getFavoriteCount() {

		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.FAVORITE_COUNT);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {
			field = jsonNode.path(TwickyConstants.TwickyJson.FAVORITE_COUNT)
					.asText();
		}
		return field;

	}

	public String getGreek() {
		return fieldValueMap.get(TwickyConstants.Table.Tweet.GREEK);
	}

	public String getID() {
		String id = parsedStatement.getWhere().getASTNode().jjtGetLastToken().image;
		return StringUtils.containsOnly(id, INVALID_CHARACTERS) ? null : id;
	}

	public String getJSON() {
		return StringUtils.replace(StringEscapeUtils.unescapeJson(fieldValueMap
				.get(TwickyConstants.Table.Tweet.JSON)), "\n", " ");
	}

	public String getRetweetCount() {

		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.RETWEET_COUNT);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {
			field = jsonNode
					.findValue(TwickyConstants.TwickyJson.RETWEET_COUNT)
					.asText();
		}
		return field;

	}

	public String getText() {

		String field = fieldValueMap.get(TwickyConstants.Table.Tweet.TEXT);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {
			field = jsonNode.findValue(TwickyConstants.TwickyJson.FULL_TEXT)
					.asText();
		}
		return StringUtils.replace(field, "\n", " ");
	}

	public String getUserScreenName() {

		String field = fieldValueMap
				.get(TwickyConstants.Table.Tweet.USER_SCREEN_NAME);
		if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode, null)) {

			field = jsonNode.path(TwickyConstants.TwickyJson.USER)
					.path(TwickyConstants.TwickyJson.SCREEN_NAME).asText();
		}
		return field;
	}

	@Override
	public String toString() {
		// return "TweetUpdateExtractor [fieldValueMap=" + fieldValueMap
		// + ", where ID =" + getID()
		// /* + ", parsedStatement=" + parsedStatement */+ "]";

		return "TweetUpdateExtractor [Blocked=" + getBlocked() + " CreatedAt="
				+ getCreatedAt() + " DiscoveredAt=" + getDiscoveredAt()
				+ " DiscovererScreenName=" + getDiscovererScreenName()
				+ " FavoriteCount=" + getFavoriteCount() + " Greek="
				+ getGreek() + " ID=" + getID() + " RetweetCount="
				+ getRetweetCount() + " Text=" + StringUtils.chomp(getText())
				+ " UserScreenName=" + getUserScreenName() + "]";

	}
}
