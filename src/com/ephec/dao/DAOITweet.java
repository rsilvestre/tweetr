package com.ephec.dao;

import com.ephec.beans.ReTweet;
import com.ephec.beans.TweetIn;
import com.ephec.beans.TweetOut;
import com.ephec.beans.User;

import java.util.List;

public interface DAOITweet {
    TweetIn createTweet(TweetIn tweet);

    List<TweetOut> getTweetOutList(User user);

    ReTweet reTweet(ReTweet reTweet);

    void deleteTweet(int userId);

    void deleteReTweet(int userId);
}
