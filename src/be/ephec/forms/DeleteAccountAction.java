package be.ephec.forms;

import be.ephec.beans.User;
import be.ephec.dao.*;

import javax.servlet.http.HttpServletRequest;

public class DeleteAccountAction extends ValidationAction {
    private final DAOUser daoUser;
    private final DAOTweet daoTweet;
    private final DAOFollow daoFollow;

    public DeleteAccountAction(DAOIUser daoIUser, DAOIFollow daoIFollow, DAOITweet daoITweet) {
        super();
        this.daoUser = (DAOUser) daoIUser;
        this.daoTweet = (DAOTweet) daoITweet;
        this.daoFollow = (DAOFollow) daoIFollow;
    }


    public void deleteAccount(HttpServletRequest request) {
        int userId = ((User) request.getSession().getAttribute(USER_SESSION)).getUserId();

        daoTweet.deleteReTweet(userId);
        daoTweet.deleteTweet(userId);
        daoFollow.deleteUserFollower(userId);
        daoFollow.deleteUserFollowing(userId);
        daoUser.delete(userId);
    }


}
