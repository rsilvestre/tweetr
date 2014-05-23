package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.forms.LoginForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Login")
public class Login extends ServletConfig {
    private static final String LOGIN = "/WEB-INF/login.jsp";
    private static final String USER = "user";
    private static final String FORM = "form";
    private static final String USERNAME = "username";

    private DAOIUser daoIUser;

    public Login() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginForm form = new LoginForm(daoIUser);

        User user = form.loginValidation(request);

        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {

            request.getSession().setAttribute(USER_SESSION, user);

            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {

                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals(USERNAME)) {

                        cookie = cooky;
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
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            request.getSession().setAttribute(USER_SESSION, null);
            this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

}
