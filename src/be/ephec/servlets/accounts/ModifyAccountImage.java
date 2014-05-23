package be.ephec.servlets.accounts;

import be.ephec.beans.User;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIUser;
import be.ephec.forms.ModifyAccountForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifyAccountImage")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "/tmp")
public class ModifyAccountImage extends ServletConfig {
    private static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    private static final String HOMEPAGE = "HomePage";
    private static final String FORM = "form";

    private DAOIUser daoIUser;
    private DAOIFile daoIFile;


    public ModifyAccountImage() {
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
        ModifyAccountForm form = new ModifyAccountForm(daoIUser, daoIFile);

        User user = form.modifyAccountImage(request);

        if (form.getErreurs().isEmpty()) {
            request.getSession().setAttribute(USER_SESSION, user);
        }

        //request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        System.out.println(form.getErreurs().containsValue("image"));
        if (form.getErreurs().isEmpty()) {
            response.sendRedirect(HOMEPAGE);
        } else {
            this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
        }
    }

}
