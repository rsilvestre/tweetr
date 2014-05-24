package be.ephec.servlets.tweets;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RetweetMessage")
public class RetweetMessage extends ServletConfig {
    private DAOITweet daoITweet;

    public RetweetMessage() {
        super();

    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.DynamicCallController(request, response, this.daoITweet);
    }

}
