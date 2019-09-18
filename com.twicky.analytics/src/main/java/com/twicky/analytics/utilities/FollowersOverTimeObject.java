package com.twicky.analytics.utilities;

public class FollowersOverTimeObject {
	
	String createdAt;
	String userId;
	String followersCount;
	String userScreenName;
	
	public String getUserScreenName() {
		return userScreenName;
	}
	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(String followersCount) {
		this.followersCount = followersCount;
	}
	
	public String toString() {
		return "User " + this.getUserScreenName() + " had " + this.getFollowersCount() + " followers at " + this.getCreatedAt();
		
	}

}
