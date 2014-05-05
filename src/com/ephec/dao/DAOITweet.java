package com.ephec.dao;

import java.util.List;

import com.ephec.beans.*;

public interface DAOITweet {
	TweetIn createTweet(TweetIn tweet);
	List<TweetOut> getTweetOutList(User user);
	ReTweet reTweet(ReTweet reTweet);
	void deleteTweet(int userId);
	void deleteReTweet(int userId);
}
