package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.ModifyAccountForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ModifyAccountInfo")
public class ModifyAccountInfo extends ServletConfig {
    public static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    public static final String HOMEPAGE = "/HomePage";
    public static final String FILE_FIELD = "fileField";
    public static final String USER = "user";
    public static final String FORM = "form";

    private DAOIUser daoIUser;
    private DAOIFile daoIFile;


    public ModifyAccountInfo() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoIFile = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        /**Préparation de l'objet formulaire*/
        ModifyAccountForm form = new ModifyAccountForm(daoIUser, daoIFile);

        /**Appel au traitement et à la validation de la requête, et récupération du bean en résultant*/
        User user = form.modifyAccountInfo(request);

        if (form.getErreurs().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        }

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            //this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
            response.sendRedirect(HOMEPAGE);
        } else {
            this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
        }
    }
}
