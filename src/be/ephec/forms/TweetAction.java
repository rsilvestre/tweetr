package be.ephec.forms;

import be.ephec.beans.ReTweet;
import be.ephec.beans.TweetIn;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOTweet;
import be.ephec.utilities.FrameworkSupport;

import javax.servlet.http.HttpServletRequest;

public class TweetAction extends ValidationAction {

    private static final String USERID = "userId";
    private static final String TWEETID = "tweetId";
    private static final String BODY = "body";

    private final DAOTweet daoTweet;

    public TweetAction(DAOITweet daoITweet) {
        this.daoTweet = (DAOTweet) daoITweet;
    }


    public TweetIn createTweet(HttpServletRequest request) {

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));

        TweetIn tweet = new TweetIn();
        tweet.setUserId(userId);
        tweet.setBody(validateData(request, BODY, null));

        if (getErreurs().isEmpty()) {
            daoTweet.createTweet(tweet);
            setResult(" Your tweet has  been sent ");
        } else {
            setResult("Your tweet has Not  been sent");
        }
        return tweet;
    }

    public ReTweet createReTweet(HttpServletRequest request) {

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));
        int tweetId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, TWEETID));

        ReTweet reTweet = new ReTweet();

        reTweet.setReTweetid(tweetId);
        reTweet.setUserId(userId);

        daoTweet.reTweet(reTweet);

        return reTweet;

    }


}
