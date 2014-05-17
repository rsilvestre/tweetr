package com.ephec.beans;

/**
 * Business object: Follow
 */
public class Follow {

    private Integer followingId;
    private Integer followerId;
    private Integer userId;
    private String updatedAt;

    public Integer getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Integer followingId) {
        this.followingId = followingId;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String pUpdatedAt) {
        this.updatedAt = pUpdatedAt;
    }

}
