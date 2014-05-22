package be.ephec.servlets.staticpage;

import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Error")
public class Error extends ServletConfig {
    private static final String HOME = "/WEB-INF/static/page404.jsp";


    public Error() {
        super();
    }

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(HOME).forward(request, response);
    }
}
