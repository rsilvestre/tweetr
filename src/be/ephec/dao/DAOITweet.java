package be.ephec.dao;

import be.ephec.beans.ReTweet;
import be.ephec.beans.TweetIn;
import be.ephec.beans.TweetOut;
import be.ephec.beans.User;

import java.util.List;

public interface DAOITweet {
    TweetIn createTweet(TweetIn tweet);

    List<TweetOut> getTweetOutList(User user);

    ReTweet reTweet(ReTweet reTweet);

    void deleteTweet(int userId);

    void deleteReTweet(int userId);
}
