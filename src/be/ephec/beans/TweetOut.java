package be.ephec.beans;

/**
 * Business object: Tweet
 */
public class TweetOut {

    /**
     * Add the joda-time-2.1 API to "Web App Librairies"
     * to manage easely the dates
     */

    private Integer tweetId;
    private String userName;
    private String body;
    private int ruid;
    private String par;
    private int uorig;
    private String uorigImage;
    private String updatedAt;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getUorigImage() {
        return uorigImage;
    }

    public void setUorigImage(String uorigImage) {
        this.uorigImage = uorigImage;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String pUpdatedAt) {
        this.updatedAt = pUpdatedAt;
    }

}
