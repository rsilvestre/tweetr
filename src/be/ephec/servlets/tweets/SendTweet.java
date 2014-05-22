package be.ephec.servlets.tweets;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.forms.SendTweetForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SendTwit
 */
@WebServlet("/SendTweet")
public class SendTweet extends ServletConfig {
    private static final String HOMEPAGE = "/HomePage";

    private DAOITweet daoITweet;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTweet() {
        super();
    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SendTweetForm form = new SendTweetForm(daoITweet);

        form.createTweet(request);
        if (!form.getErreurs().isEmpty()) {
            request.setAttribute(ERROR, form.getErreurs());
        }
        response.sendRedirect(HOMEPAGE);
    }

}
