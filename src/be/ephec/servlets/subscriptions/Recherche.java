package be.ephec.servlets.subscriptions;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.RechercheForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Recherche")
public class Recherche extends ServletConfig {
    private static final String RECHERCHE = "/WEB-INF/recherche.jsp";
    private static final String USERNOTFOLLOWINGLIST = "usernotfollowinglist";
    private static final String USERFOLLOWINGLIST = "userfollowinglist";
    private static final String KEYWORD_SESSION = "keywordSession";
    private static final String DASHBOARD = "dashboard";
    private static final String KEYWORD = "keyword";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "Recherche";
    private DAOIUser daoIUser;
    private DAOIFollow daoIFollow;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recherche() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter(KEYWORD);
        RechercheForm form = new RechercheForm(daoIUser, daoIFollow);

        if (keyword != null) {
            request.getSession().setAttribute(KEYWORD_SESSION, keyword);
        } else {
            form.createFollow(request);
            form.deleteFollow(request);
        }

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(DASHBOARD, form.getDashboardParams((User) request.getSession().getAttribute(USER_SESSION)));
        request.setAttribute(USERNOTFOLLOWINGLIST, form.searchNotFollowingByAnyNameLike(request));
        request.setAttribute(USERFOLLOWINGLIST, form.searchFollowingByAnyNameLike(request));

        this.getServletContext().getRequestDispatcher(RECHERCHE).forward(request, response);
    }
}
