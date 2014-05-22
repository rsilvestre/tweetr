package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.CreateAccountForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends ServletConfig {
    private static final String CREATEACCOUNT = "/WEB-INF/createAccount.jsp";
    private static final String HOMEPAGE = "/HomePage";
    private static final String USER = "user";
    private static final String FORM = "form";

    private DAOIUser daoIUser;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
    }


    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateAccountForm form = new CreateAccountForm(daoIUser);
        User user = form.createUserAccount(request);

        if (user == null) {
            response.sendRedirect(null);
        }
        if (form.getErreurs().isEmpty()) {
            request.getSession().setAttribute(USER_SESSION, user);
        } else {
            request.getSession().setAttribute(USER_SESSION, null);
        }

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            response.sendRedirect(HOMEPAGE);
        } else {
            this.getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(request, response);
        }
    }
}
