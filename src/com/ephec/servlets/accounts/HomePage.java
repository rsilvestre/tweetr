package com.ephec.servlets.accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.beans.TweetOut;
import com.ephec.beans.User;
import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOITweet;
import com.ephec.forms.HomePageForm;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    public static final String FORM = "form";
    public static final String USER_SESSION = "userSession";
    public static final String TWEETS = "tweets";
    public static final String USERNAME = "username";

    private DAOITweet daoITweet;


    public HomePage() {
        super();
    }

    public void init() throws ServletException {
        // Récupération d'une instance de notre DAO Utilisateur
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(
                CONF_DAO_FACTORY)).getTweetDao();

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_SESSION);

        HomePageForm form = new HomePageForm(daoITweet);

        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {

            List<TweetOut> tweets = new ArrayList<>();
            tweets = form.getTweetOutList(user);
            request.setAttribute(TWEETS, tweets);

            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}
