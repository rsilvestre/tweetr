package be.ephec.servlets.subscriptions;

import be.ephec.controller.HomepageController;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/User")
public class User extends ServletConfig {
    public static final String HOMEPAGE = "/WEB-INF/user.jsp";
    public static final String FORM = "form";
    public static final String TWEETS = "tweets";

    private DAOITweet daoITweet;
    private DAOIUser daoIUser;


    public User() {
        super();
    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomepageController form = new HomepageController(daoITweet, daoIUser);
        be.ephec.beans.User user = null;
        try {
            user = daoIUser.searchById(Integer.parseInt(request.getParameter("id")));
        } catch (Exception ex) {
            request.setAttribute(ERROR, ex.getMessage());
        }

        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            request.setAttribute(DASHBOARD, form.getDashboardParams(user));
            request.setAttribute(TWEETS, form.getTweetOutList(user));
            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
