package com.ephec.forms;

import com.ephec.beans.TweetOut;
import com.ephec.beans.User;
import com.ephec.dao.DAOITweet;
import com.ephec.dao.DAOTweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageForm {


    private Map<String, String> erreurs = new HashMap<String, String>();
    private DAOTweet daoTweet;


    public HomePageForm(DAOITweet daoITweet) {
        this.daoTweet = (DAOTweet) daoITweet;
    }


    public List<TweetOut> getTweetOutList(User user) {
        List<TweetOut> tweetsOut = daoTweet.getTweetOutList(user);
        return tweetsOut;
    }


    public Map<String, String> getErreurs() {
        return erreurs;
    }

}
