package be.ephec.servlets.home;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.HomePageForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HomePage")
public class HomePage extends ServletConfig {
    private static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    private static final String FORM = "form";
    private static final String TWEETS = "tweets";

    private DAOITweet daoITweet;
    private DAOIUser daoIUser;


    public HomePage() {
        super();
    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomePageForm form = new HomePageForm(daoITweet, daoIUser);

        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            request.setAttribute(DASHBOARD, form.getDashboardParams((User) request.getSession().getAttribute(USER_SESSION)));
            request.setAttribute(TWEETS, form.getTweetOutList((User) request.getSession().getAttribute(USER_SESSION)));
            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
