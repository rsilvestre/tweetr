package be.ephec.servlets.accounts;


import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.HomePageForm;
import be.ephec.forms.RechercheForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowAccount")
public class ShowAccount extends ServletConfig {
    public static final String TWEETS = "tweets";
    public static final String DASHBOARD = "dashboard";
    private static final String SHOWACCOUNT = "/WEB-INF/showAccount.jsp";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "ShowAccount";

    private DAOIFollow daoIFollow;
    private DAOITweet daoITweet;
    private DAOIUser daoIUser;

    public ShowAccount() {
        super();
    }

    public void init() throws ServletException {
        daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        User user;
        if ((userId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(userId), (User) request.getSession().getAttribute(USER_SESSION))) == null) {
            user = (User) request.getSession().getAttribute(USER_SESSION);
        }

        HomePageForm form = new HomePageForm(daoITweet, daoIUser);

        request.setAttribute("user", user);
        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(DASHBOARD, form.getDashboardParams(user));
        request.setAttribute(TWEETS, form.getTweetOutList(user));
        this.getServletContext().getRequestDispatcher(SHOWACCOUNT).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RechercheForm formFlow = new RechercheForm(daoIUser, daoIFollow);

        formFlow.createFollow(request);
        formFlow.deleteFollow(request);

        String userId;
        User user;
        if ((userId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(userId), (User) request.getSession().getAttribute(USER_SESSION))) == null) {
            user = (User) request.getSession().getAttribute(USER_SESSION);
        }

        HomePageForm formShowAccount = new HomePageForm(daoITweet, daoIUser);

        request.setAttribute("user", user);
        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(DASHBOARD, formShowAccount.getDashboardParams(user));
        request.setAttribute(TWEETS, formShowAccount.getTweetOutList(user));
        this.getServletContext().getRequestDispatcher(SHOWACCOUNT).forward(request, response);
    }
}
