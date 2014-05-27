package be.ephec.servlets.home;

import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Home")
public class Home extends ServletConfig {
    private static final String HOME = "/WEB-INF/home.jsp";


    public Home() {
        super();
    }

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
    }
}
