package com.ephec.servlets.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.beans.User;
import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOIFile;
import com.ephec.dao.DAOIUser;
import com.ephec.forms.ModifyAccountForm;

/**
 * Servlet implementation class ModifyAccountImage
 */
@WebServlet("/ModifyAccountImage")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15, // 15 MB
        location            = "C:/TweetrFiles")
public class ModifyAccountImage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
	public static final String HOMEPAGE = "EphecTweetr/HomePage";
	public static final String FILE_FIELD = "fileField";
	public static final String USER = "user";
	public static final String FORM = "form";
	public static final String USER_SESSION = "userSession";
	
	private DAOIUser daoIUser;
	private DAOIFile daoIFile;

	
	public ModifyAccountImage() {
		super();
	}

	public void init() throws ServletException {
		this.daoIUser = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
		this.daoIFile = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getFileDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(MODIFYACCOUNT)
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		/**Préparation de l'objet formulaire*/
		ModifyAccountForm form = new ModifyAccountForm(daoIUser, daoIFile);
		
		/**Appel au traitement et à la validation de la requête, et récupération du bean en résultant*/
		User user = form.modifyAccountImage(request);
		
		

		if (form.getErreurs().isEmpty() ) {
		session.setAttribute( USER_SESSION, user );
		} 
		
		/**Stockage du formulaire et du bean dans l'objet request*/
		//request.setAttribute(USER, user);
		request.setAttribute(FORM, form);
		
		System.out.println(form.getErreurs().containsValue("profileImage"));
		if (form.getErreurs().isEmpty()) {
			//this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
			response.sendRedirect(HOMEPAGE);
		} else {

			this.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
		}
	}

}
