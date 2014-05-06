package com.ephec.servlets.accounts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.beans.User;
import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOIUser;
import com.ephec.forms.LoginForm;


@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String LOGIN = "/WEB-INF/login.jsp";
    public static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    public static final String USER = "user";
    public static final String FORM = "form";
    public static final String USER_SESSION = "userSession";
    public static final String USERNAME = "username";

    private DAOIUser daoIUser;

    public Login() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(
                CONF_DAO_FACTORY)).getUserDao();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginForm form = new LoginForm(daoIUser);

        /**
         * Appel au traitement et à la validation de la requête, et récupération
         * du bean en résultant
         */
        User user = form.loginValidation(request);

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /** Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        System.out.println(form.getErreurs().isEmpty());
        if (form.getErreurs().isEmpty()) {

            session.setAttribute(USER_SESSION, user);

            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {

                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals(USERNAME)) {

                        cookie = cookies[i];
                        if (!(cookie.getValue().equals(user.getUserName()))) {
                            cookie.setValue(user.getUserName());
                            response.addCookie(cookie);
                        }
                    }
                }
            }

            if (cookie == null) {

                cookie = new Cookie(USERNAME, user.getUserName());
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            //response.sendRedirect(HOMEPAGE);
            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
        } else {
            session.setAttribute(USER_SESSION, null);
            //response.sendRedirect(LOGIN);
            this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

}
