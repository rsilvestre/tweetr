package be.ephec.beans;

/**
 * Business object: Twit
 */
public class TweetIn {

    /**
     * Add the joda-time-2.1 API to "Web App Librairies"
     * to manage easely the dates
     */

    private Integer twitId;
    private Integer userId;
    private String body;
    private String updatedAt;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String pUpdatedAt) {
        this.updatedAt = pUpdatedAt;
    }

}
