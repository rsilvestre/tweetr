package be.ephec.servlets.tweets;

import be.ephec.beans.ReTweet;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.filters.RestrictAccess;
import be.ephec.forms.TweetForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Retweet")
public class Retweet extends ServletConfig {
    private static final String RETWEET = "reTweet";
    private static final String FORM = "form";
    private DAOITweet daoITweet;

    public Retweet() {
        super();

    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TweetForm form = new TweetForm(daoITweet);

        ReTweet reTweet = form.createReTweet(request);

        request.setAttribute(RETWEET, reTweet);
        request.setAttribute(FORM, form);
        response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
    }

}
