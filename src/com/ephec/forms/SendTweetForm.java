package com.ephec.forms;

import com.ephec.beans.ReTweet;
import com.ephec.beans.TweetIn;
import com.ephec.dao.DAOITweet;
import com.ephec.dao.DAOTweet;
import com.ephec.utilities.FrameworkSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Create and Send Tweet
 */
public class SendTweetForm {

    private static final String USERID = "userId";
    private static final String TWEETID = "tweetId";
    private static final String BODY = "body";

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

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));
        String body = FrameworkSupport.getTrimedValue(request, BODY);

        TweetIn tweet = new TweetIn();

        // Data validation

        try {
            bodyValidation(body);
        } catch (Exception e) {
            setErreur(BODY, e.getMessage());
        }

        tweet.setUserId(userId);
        tweet.setBody(body);

        if (erreurs.isEmpty()) {
            daoTweet.createTweet(tweet);
            result = " Your tweet has  been sent ";
        } else {
            result = "Your tweet has Not  been sent";
        }
        return tweet;
    }

    public ReTweet sendReTweet(HttpServletRequest request) {

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));
        int tweetId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, TWEETID));

        ReTweet reTweet = new ReTweet();

        reTweet.setReTweetid(tweetId);
        reTweet.setUserId(userId);

        daoTweet.reTweet(reTweet);

        return reTweet;

    }

    private void setErreur(String body2, String body3) {
        // TODO Auto-generated method stub
        erreurs.put(body2, body3);
    }

    /**
     * Tweet name validation
     */
    private void bodyValidation(String body) throws Exception {
        if (body != null && body.trim().length() < 3) {
            throw new Exception("The tweet must contain at least 3 characters.");
        }

        if (body.length() > 140) {
            throw new Exception("The tweet is too long.");
        }
    }


}
