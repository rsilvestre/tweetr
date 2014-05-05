package com.ephec.servlets.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOIFollow;
import com.ephec.dao.DAOITweet;
import com.ephec.dao.DAOIUser;
import com.ephec.forms.DeleteAccountForm;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOIUser daoIUser;
	private DAOITweet daoITweet;
	private DAOIFollow daoIFollow;
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String LOGIN = "/WEB-INF/Login.jsp";
	public static final String USER_SESSION = "userSession";
    public DeleteAccount() {
        super();
        }
    public void init() throws ServletException {
    	this.daoIFollow = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFollowDao();
		this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
		this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	DeleteAccountForm form = new DeleteAccountForm(daoIUser, daoIFollow, daoITweet);
	form.deleteAccount(request);
	HttpSession session = request.getSession();
	session.setAttribute(USER_SESSION, null);
	this.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
