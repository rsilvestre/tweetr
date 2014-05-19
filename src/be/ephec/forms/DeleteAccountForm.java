package be.ephec.forms;

import be.ephec.beans.User;
import be.ephec.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteAccountForm extends ValidationForm {
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
