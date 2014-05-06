package com.ephec.beans;

/**
 * Business object: RetWit
 */
public class ReTweet {

    /** Add the joda-time-2.1 API to "Web App Librairies"
     * to manage easely the dates */

    /**
     * The attribut "comment" is not necessary because it's not
     * possible to comment when you retwit.
     */
    private Integer reTweetid;
    private Integer tweetId;
    private Integer userId;
    private String timestamp;

    public Integer getReTweetid() {
        return reTweetid;
    }

    public void setReTweetid(Integer reTweetid) {
        this.reTweetid = reTweetid;
    }

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
