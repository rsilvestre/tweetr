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
    private String updatedAt;

    public Integer getReTweetid() {
        return reTweetid;
    }

    public void setReTweetid(Integer pReTweetid) {
        this.reTweetid = pReTweetid;
    }

    public Integer getTweetId() {
        return this.tweetId;
    }

    public void setTweetId(Integer pTweetId) {
        this.tweetId = pTweetId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer pUserId) {
        this.userId = pUserId;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String pUpdatedAt) {
        this.updatedAt = pUpdatedAt;
    }
}
