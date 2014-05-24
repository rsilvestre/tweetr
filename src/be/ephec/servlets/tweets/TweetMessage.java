package be.ephec.servlets.tweets;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TweetMessage")
public class TweetMessage extends ServletConfig {
    private static final String TWEET = "/WEB-INF/tweet.jsp";
    private static final String TWEETS = "tweets";
    private DAOITweet daoITweet;
    private DAOIUser daoIUser;

    public TweetMessage() {
        super();
    }

    public void init() throws ServletException {
        daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
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

        request.setAttribute(TWEETS, daoITweet.getLstTweetOutByUser(user));
        request.setAttribute(DASHBOARD, daoIUser.getDashboard(user));
        this.getServletContext().getRequestDispatcher(TWEET).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.DynamicCallController(request, response, this.daoITweet);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ERROR, e.getMessage());
            request.getRequestDispatcher(RestrictAccess.PageInOut.ERROR.toString()).forward(request, response);
        }
    }
}
