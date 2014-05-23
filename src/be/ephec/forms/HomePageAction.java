package be.ephec.forms;

import be.ephec.beans.Dashboard;
import be.ephec.beans.TweetOut;
import be.ephec.beans.User;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOTweet;
import be.ephec.dao.DAOUser;

import java.util.List;

public class HomePageAction extends ValidationAction {

    private final DAOTweet daoTweet;
    private final DAOUser daoUser;

    public HomePageAction(DAOITweet daoITweet, DAOIUser daoIUser) {
        this.daoTweet = (DAOTweet) daoITweet;
        this.daoUser = (DAOUser) daoIUser;
    }

    public List<TweetOut> getTweetOutList(User user) {
        return daoTweet.getTweetOutList(user);
    }

    public Dashboard getDashboardParams(User user) {
        return daoUser.getDashboard(user);
    }
}
