package be.ephec.servlets.accounts;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/LoginAccount")
public class LoginAccount extends ServletConfig {
    private static final String LOGIN = "/WEB-INF/login.jsp";

    private DAOIUser daoIUser;

    public LoginAccount() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.DynamicCallController(request, response, this.daoIUser);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ERROR, e.getMessage());
            request.getRequestDispatcher(RestrictAccess.PAGE_ERROR).forward(request, response);
        }
    }

}
