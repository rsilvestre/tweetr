package com.ephec.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ephec.beans.ReTweet;
import com.ephec.beans.TweetIn;
import com.ephec.beans.User;
import com.ephec.dao.DAOITweet;
import com.ephec.dao.DAOTweet;
import com.ephec.utility.UserUtility;

/** Create and Send Tweet */
public class SendTweetForm {

	private static final String USERID = "userId";
	private static final String TWEETID = "tweetId";
	private static final String MESSAGE = "message";

	private String result;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private DAOTweet daoTweet;

	public SendTweetForm(DAOITweet daoITweet) {
		this.daoTweet = (DAOTweet) daoITweet;
	}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	// Retrieve the data entered by the user

	public TweetIn createTweet(HttpServletRequest request) {

		int userId = Integer.parseInt(UserUtility.getFieldValue(request, USERID));
		String message = UserUtility.getFieldValue(request, MESSAGE);

		TweetIn tweet = new TweetIn();
		
		// Data validation
		
		try {
			messageValidation(message);
		} catch (Exception e) {
			setErreur(MESSAGE, e.getMessage());
		}
		
		tweet.setUserId(userId);
		tweet.setMessage(message);
		
		if (erreurs.isEmpty()) {
			daoTweet.createTweet(tweet);
			 result = " Your tweet has  been sent ";
		}
		else {
			result = "Your tweet has Not  been sent";
		}
		return tweet;
	}
	
	public ReTweet sendReTweet(HttpServletRequest request)
	{
		
		int userId = Integer.parseInt(UserUtility.getFieldValue(request, USERID));
		int tweetId = Integer.parseInt(UserUtility.getFieldValue(request, TWEETID));
		
		ReTweet reTweet = new ReTweet();
		
		reTweet.setReTweetid(tweetId);
		reTweet.setUserId(userId);
		
		daoTweet.reTweet(reTweet);
		
		return reTweet;
		
	}

	private void setErreur(String message2, String message3) {
		// TODO Auto-generated method stub
		erreurs.put(message2, message3);
	}

	/**
	 * Tweet name validation
	 */
	private void messageValidation(String message) throws Exception {
		if (message != null && message.trim().length() < 3) {
			throw new Exception("The tweet must contain at least 3 characters.");
		}

		if (message.length() > 140) {
			throw new Exception("The tweet is too long.");
		}
	}
	

}
