package be.ephec.servlets.tweets;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Tweet")
public class Tweet extends ServletConfig {
    public static final String DASHBOARD = "dashboard";
    private static final String TWEET = "/WEB-INF/tweet.jsp";
    private static final String TWEETS = "tweets";
    private DAOITweet daoITweet;
    private DAOIUser daoIUser;

    public Tweet() {
        super();
    }

    public void init() throws ServletException {
        daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
        daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(TWEETS, daoITweet.getLstTweetOutByUser((User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard((User) request.getSession().getAttribute(USER_SESSION)));
        this.getServletContext().getRequestDispatcher(TWEET).forward(request, response);
    }
}
