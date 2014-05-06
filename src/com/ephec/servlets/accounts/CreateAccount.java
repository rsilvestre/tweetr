package com.ephec.servlets.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.beans.User;
import com.ephec.dao.DAOIUser;
import com.ephec.forms.CreateAccountForm;
import com.ephec.dao.DAOFactory;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String CREATEACCOUNT = "/WEB-INF/createAccount.jsp";
    public static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    public static final String USER = "user";
    public static final String FORM = "form";
    public static final String USER_SESSION = "userSession";

    private DAOIUser daoIUser;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void init() throws ServletException {
        //Récupération d'une instance de notre DAO Utilisateur
        this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        /**Display createAccount.jsp*/
        this.getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        /**Préparation de l'objet formulaire*/
        CreateAccountForm form = new CreateAccountForm(daoIUser);

        /**Appel au traitement et à la validation de la requête, et récupération du bean en résultant*/
        User user = form.createUserAccount(request);


        if (user == null) {
            response.sendRedirect(null);
        }
        if (form.getErreurs().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        } else {
            session.setAttribute(USER_SESSION, null);
        }

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {
            this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);

        } else {

            this.getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(request, response);
        }

    }
}
