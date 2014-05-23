package be.ephec.beans;

public class ReTweet {
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
