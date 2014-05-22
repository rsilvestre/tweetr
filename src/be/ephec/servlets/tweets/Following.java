package be.ephec.servlets.tweets;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.RechercheForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Following")
public class Following extends ServletConfig {
    public static final String DASHBOARD = "dashboard";
    private static final String FOLLOWING = "/WEB-INF/following.jsp";
    private static final String USERFOLLOWINGLIST = "userfollowinglist";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "Following";

    private DAOIFollow daoIFollow;
    private DAOIUser daoIUser;

    public Following() {
        super();
    }

    public void init() throws ServletException {
        daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();
        daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(USERFOLLOWINGLIST, daoIFollow.getFollowingByUser((User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard((User) request.getSession().getAttribute(USER_SESSION)));
        this.getServletContext().getRequestDispatcher(FOLLOWING).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RechercheForm form = new RechercheForm(daoIUser, daoIFollow);

        form.createFollow(request);
        form.deleteFollow(request);

        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(USERFOLLOWINGLIST, daoIFollow.getFollowingByUser((User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard((User) request.getSession().getAttribute(USER_SESSION)));
        this.getServletContext().getRequestDispatcher(FOLLOWING).forward(request, response);
    }
}
