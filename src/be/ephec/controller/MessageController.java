package be.ephec.controller;

import be.ephec.beans.ReTweet;
import be.ephec.beans.TweetIn;
import be.ephec.dao.DAOITweet;
import be.ephec.filters.RestrictAccess;
import be.ephec.utilities.FrameworkSupport;

import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageController extends ValidationController {
    private static final String ERROR = "error";

    private static final String USERID = "userId";
    private static final String TWEETID = "tweetId";
    private static final String BODY = "body";
    private static final String RETWEET = "reTweet";

    private final GenericServlet servlet;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public MessageController(GenericServlet servlet, HttpServletRequest request, HttpServletResponse response) {
        super();
        this.servlet = servlet;
        this.request = request;
        this.response = response;
    }


    public void Tweet(Object... objects) throws IOException {
        DAOITweet daoITweet = (DAOITweet) objects[0];

        this.createTweet(daoITweet);
        if (!this.getErreurs().isEmpty()) {
            request.setAttribute(ERROR, this.getErreurs());
        }
        response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
    }

    public void Retweet(Object... objects) throws IOException {
        DAOITweet daoITweet = (DAOITweet) objects[0];

        ReTweet reTweet = this.createReTweet(daoITweet);

        request.setAttribute(RETWEET, reTweet);
        response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
    }

    private TweetIn createTweet(DAOITweet daoITweet) {

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));

        TweetIn tweet = new TweetIn();
        tweet.setUserId(userId);
        tweet.setBody(validateData(request, BODY, null));

        if (getErreurs().isEmpty()) {
            daoITweet.createTweet(tweet);
            setResult(" Your tweet has  been sent ");
        } else {
            setResult("Your tweet has Not  been sent");
        }
        return tweet;
    }

    private ReTweet createReTweet(DAOITweet daoITweet) {

        int userId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, USERID));
        int tweetId = Integer.parseInt(FrameworkSupport.getTrimedValue(request, TWEETID));

        ReTweet reTweet = new ReTweet();

        reTweet.setReTweetid(tweetId);
        reTweet.setUserId(userId);

        daoITweet.reTweet(reTweet);

        return reTweet;

    }


}
