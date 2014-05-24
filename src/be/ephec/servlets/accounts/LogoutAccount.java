package be.ephec.servlets.accounts;

import be.ephec.filters.RestrictAccess;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutAccount")
public class LogoutAccount extends ServletConfig {

    public LogoutAccount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(USER_SESSION, null);
        response.sendRedirect(RestrictAccess.PageOut.HOME.toString());
    }
}
