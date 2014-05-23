package be.ephec.servlets.subscriptions;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.SearchForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Follower")
public class Follower extends ServletConfig {
    private static final String FOLLOWER = "/WEB-INF/follower.jsp";
    private static final String USERFOLLOWINGLIST = "userfollowerlist";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "Follower";

    private DAOIFollow daoIFollow;
    private DAOIUser daoIUser;

    public Follower() {
        super();
    }

    public void init() throws ServletException {
        daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();
        daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        String stringParamId;
        try {
            if ((stringParamId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(stringParamId), (User) request.getSession().getAttribute(USER_SESSION))) == null) {
                user = (User) request.getSession().getAttribute(USER_SESSION);
            }
        } catch (Exception ex) {
            request.setAttribute(ERROR, ex.getMessage());
        }

        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(USERFOLLOWINGLIST, daoIFollow.getFollowerByUser(user, (User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard(user));
        this.getServletContext().getRequestDispatcher(FOLLOWER).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchForm form = new SearchForm(daoIUser, daoIFollow);

        form.createFollow(request);
        form.deleteFollow(request);

        User user = null;
        String stringParamId;
        try {
            if ((stringParamId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(stringParamId), (User) request.getSession().getAttribute(USER_SESSION))) == null) {
                user = (User) request.getSession().getAttribute(USER_SESSION);
            }
        } catch (Exception ex) {
            request.setAttribute(ERROR, ex.getMessage());
        }

        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(USERFOLLOWINGLIST, daoIFollow.getFollowerByUser(user, (User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard(user));
        this.getServletContext().getRequestDispatcher(FOLLOWER).forward(request, response);
    }
}
