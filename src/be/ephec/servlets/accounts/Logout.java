package be.ephec.servlets.accounts;

import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SignOut
 */
@WebServlet("/Logout")
public class Logout extends ServletConfig {
    public static final String HOME = "/WEB-INF/home.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(USER_SESSION, null);
        this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
    }

}
