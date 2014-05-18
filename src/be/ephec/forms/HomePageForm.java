package be.ephec.forms;

import be.ephec.beans.TweetOut;
import be.ephec.beans.User;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOTweet;

import java.util.List;

public class HomePageForm extends ValidationForm {

    private DAOTweet daoTweet;

    public HomePageForm(DAOITweet daoITweet) {
        this.daoTweet = (DAOTweet) daoITweet;
    }

    public List<TweetOut> getTweetOutList(User user) {
        List<TweetOut> tweetsOut = daoTweet.getTweetOutList(user);
        return tweetsOut;
    }
}
