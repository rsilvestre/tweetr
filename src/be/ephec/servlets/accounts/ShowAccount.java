package be.ephec.servlets.accounts;


import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIUser;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowAccount")
public class ShowAccount extends ServletConfig {
    public static final String SHOWACCOUNT = "/WEB-INF/showAccount.jsp";

    private DAOIUser daoIUser;

    public ShowAccount() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        User user = null;
        if ((userId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(userId))) == null) {
            user = (User) request.getSession().getAttribute(USER_SESSION);
        }
        request.setAttribute("user", user);
        this.getServletContext().getRequestDispatcher(SHOWACCOUNT).forward(request, response);
    }
}
