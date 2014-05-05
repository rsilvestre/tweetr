package com.ephec.beans;

/**
* Business object: Twit
* */
public class TweetIn {

	/** Add the joda-time-2.1 API to "Web App Librairies"
	 * to manage easely the dates */
	
	private Integer twitId;
	private Integer userId;
	private String message;
	private String timestamp;
	
	public Integer getTwitId() {
		return twitId;
	}
	public void setTwitId(Integer twitId) {
		this.twitId = twitId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
