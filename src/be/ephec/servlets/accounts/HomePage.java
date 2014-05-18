package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.forms.HomePageForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends ServletConfig {
    public static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    public static final String FORM = "form";
    public static final String USER_SESSION = "userSession";
    public static final String TWEETS = "tweets";

    private DAOITweet daoITweet;


    public HomePage() {
        super();
    }

    public void init() throws ServletException {
        // Récupération d'une instance de notre DAO Utilisateur
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomePageForm form = new HomePageForm(daoITweet);

        request.setAttribute(FORM, new HomePageForm(daoITweet));

        if (form.getErreurs().isEmpty()) {
            request.setAttribute(TWEETS, form.getTweetOutList((User) request.getSession().getAttribute(USER_SESSION)));
            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
