package be.ephec.servlets.accounts;

import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ImageAccount")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15, // 15 MB
        location = "/tmp")
public class ImageAccount extends ServletConfig {
    private static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    private static final String FORM = "form";

    private DAOIUser daoIUser;
    private DAOIFile daoIFile;


    public ImageAccount() {
        super();
    }

    public void init() throws ServletException {
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
        this.daoIFile = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileDao();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.DynamicCallController(request, response, this.daoIUser, this.daoIFile);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ERROR, e.getMessage());
            request.getRequestDispatcher(RestrictAccess.PAGE_ERROR).forward(request, response);
        }
    }

}
