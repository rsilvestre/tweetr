package com.ephec.forms;

import com.ephec.beans.User;
import com.ephec.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class DeleteAccountForm {
    private final String USER_SESSION = "userSession";
    private String result;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private DAOUser daoUser;
    private DAOTweet daoTweet;
    private DAOFollow daoFollow;
    private User user;

    public DeleteAccountForm(DAOIUser daoIUser, DAOIFollow daoIFollow, DAOITweet daoITweet) {
        super();
        this.daoUser = (DAOUser) daoIUser;
        this.daoTweet = (DAOTweet) daoITweet;
        this.daoFollow = (DAOFollow) daoIFollow;
    }


    public void deleteAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        user = (User) session.getAttribute(USER_SESSION);
        int userId = user.getUserId();

        daoTweet.deleteReTweet(userId);
        daoTweet.deleteTweet(userId);
        daoFollow.deleteUserFollower(userId);
        daoFollow.deleteUserFollowing(userId);
        daoUser.delete(userId);

    }


}
