package be.ephec.beans;

/**
 * Created by michaelsilvestre on 19/05/14.
 */
public class Dashboard {
    private int tweetNumber;
    private int followerNumber;
    private int followingNumber;
    private User user;


    public int getFollowingNumber() {
        return followingNumber;
    }

    public void setFollowingNumber(int followingNumber) {
        this.followingNumber = followingNumber;
    }

    public int getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(int followerNumber) {
        this.followerNumber = followerNumber;
    }

    public int getTweetNumber() {
        return tweetNumber;
    }

    public void setTweetNumber(int tweetNumber) {
        this.tweetNumber = tweetNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
