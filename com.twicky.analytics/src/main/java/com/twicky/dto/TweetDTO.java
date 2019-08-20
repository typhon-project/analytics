package com.twicky.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.twicky.extractors.update.extractor.TweetUpdateExtractor;

public class TweetDTO {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEE MMM dd hh:mm:ss +0000 yyyy");

	private String blocked;
	private String createdAt;
	private String discoveredAt;
	private String discovererScreenName;
	private String favoriteCount;
	private String greek;
	private String id;
	private String json;
	private String retweetCount;
	private String text;
	private String userScreenName;

	public TweetDTO() {
		super();

	}

	public TweetDTO(TweetUpdateExtractor extractor) {
		super();
		this.blocked = extractor.getBlocked();
		this.createdAt = extractor.getCreatedAt();
		this.discoveredAt = extractor.getDiscoveredAt();
		this.discovererScreenName = extractor.getDiscovererScreenName();
		this.favoriteCount = extractor.getFavoriteCount();
		this.greek = extractor.getGreek();
		this.id = extractor.getID();
		this.json = extractor.getJSON();
		this.retweetCount = extractor.getRetweetCount();
		this.text = extractor.getText();
		this.userScreenName = extractor.getUserScreenName();

	}

	public String getBlocked() {
		return blocked;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getDiscoveredAt() {
		return discoveredAt;
	}

	public String getDiscovererScreenName() {
		return discovererScreenName;
	}

	public String getFavoriteCount() {
		return favoriteCount;
	}

	public String getGreek() {
		return greek;
	}

	public String getId() {
		return id;
	}

	public String getJson() {
		return json;
	}

	public String getRetweetCount() {
		return retweetCount;
	}

	public String getText() {
		return text;
	}

	public String getUserScreenName() {
		return userScreenName;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setDiscoveredAt(String discoveredAt) {
		this.discoveredAt = discoveredAt;
	}

	public void setDiscovererScreenName(String discovererScreenName) {
		this.discovererScreenName = discovererScreenName;
	}

	public void setFavoriteCount(String favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public void setGreek(String greek) {
		this.greek = greek;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public void setRetweetCount(String retweetCount) {
		this.retweetCount = retweetCount;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public Long retrieveCreatedAtTimestamp() {
		Long time = null;
		try {
			if (StringUtils.isNoneBlank(createdAt)) {

				time = new Long(dateFormat.parse(createdAt).getTime());

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	@Override
	public String toString() {
		return "TweetDTO [blocked=" + blocked + ", createdAt=" + createdAt
				+ ", discoveredAt=" + discoveredAt + ", discovererScreenName="
				+ discovererScreenName + ", favoriteCount=" + favoriteCount
				+ ", greek=" + greek + ", id=" + id + ", json=" + json
				+ ", retweetCount=" + retweetCount + ", text=" + text
				+ ", userScreenName=" + userScreenName + "]";
	}

}
