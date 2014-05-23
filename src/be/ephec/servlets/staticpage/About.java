package be.ephec.servlets.staticpage;

import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/About")
public class About extends ServletConfig {
    private static final String ABOUT = "/WEB-INF/about.jsp";

    public About() {
        super();
    }

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(ABOUT).forward(request, response);
    }
}
