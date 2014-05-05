package com.ephec.beans;

/**
* Business object: Tweet
* */
public class TweetOut {

	/** Add the joda-time-2.1 API to "Web App Librairies"
	 * to manage easely the dates */
	
	private Integer tweetId;
	private String userName;
	private String message;
	private int ruid;
	private String par;
	private int uorig;
	private String timestamp;
	
	public Integer getTweetId() {
		return tweetId;
	}
	public void setTweetId(Integer twitId) {
		this.tweetId = twitId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRuid() {
		return ruid;
	}
	public void setRuid(int ruid) {
		this.ruid = ruid;
	}
	public String getPar() {
		return par;
	}
	public void setPar(String par) {
		this.par = par;
	}
	public int getUorig() {
		return uorig;
	}
	public void setUorig(int uorig) {
		this.uorig = uorig;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
