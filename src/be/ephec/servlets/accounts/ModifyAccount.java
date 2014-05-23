package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.forms.ModifyAccountForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/ModifyAccount")
public class ModifyAccount extends ServletConfig {

    private static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    private static final String USER = "user";
    private static final String FORM = "form";

    private DAOIUser daoIUser;
    private DAOIFile daoIFile;

    public ModifyAccount() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoIFile = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ModifyAccountForm form = new ModifyAccountForm(daoIUser, daoIFile);

        User user = form.modifyAccountInfo(request);

        if (form.getErreurs().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        }

        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
        }
    }

}
