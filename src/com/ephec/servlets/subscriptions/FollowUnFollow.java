package com.ephec.servlets.subscriptions;

import com.ephec.beans.User;
import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOIFollow;
import com.ephec.dao.DAOIUser;
import com.ephec.forms.FollowUnfollowForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/FollowUnFollow")
public class FollowUnFollow extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String FOLLOWUNFOLLOW = "/WEB-INF/followUnfollow.jsp";
    public static final String USERNOTFOLLOWINGLIST = "usernotfollowinglist";
    public static final String USERFOLLOWINGLIST = "userfollowinglist";
    public static final String KEYWORD_SESSION = "keywordSession";
    private static final long serialVersionUID = 1L;
    private static final String KEYWORD = "keyword";
    private DAOIUser daoIUser;
    private DAOIFollow daoIFollow;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowUnFollow() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String keyword = request.getParameter(KEYWORD);
        FollowUnfollowForm form = new FollowUnfollowForm(daoIUser, daoIFollow);

        if (keyword != null) {
            session.setAttribute(KEYWORD_SESSION, keyword);
        } else {
            form.createFollow(request);
            form.deleteFollow(request);
        }

        List<User> userNotFollowingList = new ArrayList<>();
        List<User> userFollowingList = new ArrayList<>();

        userNotFollowingList = form.searchNotFollowingByAnyNameLike(request);
        userFollowingList = form.searchFollowingByAnyNameLike(request);

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(USERNOTFOLLOWINGLIST, userNotFollowingList);
        request.setAttribute(USERFOLLOWINGLIST, userFollowingList);

        this.getServletContext().getRequestDispatcher(FOLLOWUNFOLLOW).forward(request, response);
    }
}
