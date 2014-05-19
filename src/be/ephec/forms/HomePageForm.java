package be.ephec.forms;

import be.ephec.beans.Dashboard;
import be.ephec.beans.TweetOut;
import be.ephec.beans.User;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOTweet;
import be.ephec.dao.DAOUser;

import java.util.List;

public class HomePageForm extends ValidationForm {

    private DAOTweet daoTweet;
    private DAOUser daoUser;

    public HomePageForm(DAOITweet daoITweet, DAOIUser daoIUser) {
        this.daoTweet = (DAOTweet) daoITweet;
        this.daoUser = (DAOUser) daoIUser;
    }

    public List<TweetOut> getTweetOutList(User user) {
        List<TweetOut> tweetsOut = daoTweet.getTweetOutList(user);
        return tweetsOut;
    }

    public Dashboard getDashboardParams(User user) {
        Dashboard dashboard = daoUser.getDashboard(user);
        return dashboard;
    }
}
