package be.ephec.servlets.accounts;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.DeleteAccountForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/DeleteAccount")
public class DeleteAccount extends ServletConfig {

    public static final String LOGIN = "/WEB-INF/Login.jsp";
    public static final String USER_SESSION = "userSession";
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
        DeleteAccountForm form = new DeleteAccountForm(daoIUser, daoIFollow, daoITweet);
        form.deleteAccount(request);
        HttpSession session = request.getSession();
        session.setAttribute(USER_SESSION, null);
        this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
