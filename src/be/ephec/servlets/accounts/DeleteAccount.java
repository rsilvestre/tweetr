package be.ephec.servlets.accounts;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.forms.DeleteAccountAction;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends ServletConfig {

    private static final String LOGIN = "/WEB-INF/login.jsp";
    private DAOIUser daoIUser;
    private DAOITweet daoITweet;
    private DAOIFollow daoIFollow;

    public DeleteAccount() {
        super();
    }

    public void init() throws ServletException {
        this.daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeleteAccountAction form = new DeleteAccountAction(daoIUser, daoIFollow, daoITweet);
        form.deleteAccount(request);
        response.sendRedirect(RestrictAccess.PageIn.LOGOUT.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
