package be.ephec.forms;

import be.ephec.beans.Dashboard;
import be.ephec.beans.User;
import be.ephec.dao.DAOFollow;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOUser;
import be.ephec.utilities.FrameworkSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RechercheForm extends ValidationForm {
    private static final String KEYWORD_SESSION = "keywordSession";

    private DAOUser daoUser;
    private DAOFollow daoFollow;

    public RechercheForm(DAOIUser daoIUser, DAOIFollow daoIFollow) {
        this.daoUser = (DAOUser) daoIUser;
        this.daoFollow = (DAOFollow) daoIFollow;
    }

    public List<User> searchFollowingByAnyNameLike(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_SESSION);
        String keyword = (String) session.getAttribute(KEYWORD_SESSION);
        return daoUser.searchFollowingByAnyNameLike(keyword, user.getUserId());
    }

    public List<User> searchNotFollowingByAnyNameLike(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_SESSION);
        String keyword = (String) session.getAttribute(KEYWORD_SESSION);
        return daoUser.searchNotFollowingByAnyNameLike(keyword, user.getUserId());
    }

    public void createFollow(HttpServletRequest request) {
        String followingId = FrameworkSupport.getTrimedValue(request, "follow");

        if (!followingId.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(USER_SESSION);
            daoFollow.create(user, followingId);
        }
    }

    public void deleteFollow(HttpServletRequest request) {
        String followingId = FrameworkSupport.getTrimedValue(request, "stopfollow");
        if (!followingId.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(USER_SESSION);
            daoFollow.delete(user, followingId);
        }
    }


    public Object getDashboardParams(User user) {
        Dashboard dashboard = daoUser.getDashboard(user);
        return dashboard;
    }
}
