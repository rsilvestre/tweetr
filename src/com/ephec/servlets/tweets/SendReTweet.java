package com.ephec.servlets.tweets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ephec.beans.ReTweet;
import com.ephec.dao.DAOFactory;
import com.ephec.dao.DAOITweet;
import com.ephec.forms.SendTweetForm;

/**
 * Servlet implementation class ReTweet
 */
@WebServlet("/SendReTweet")
public class SendReTweet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String HOMEPAGE = "/EphecTweetr/HomePage";
    public static final String RETWEET = "reTweet";
    public static final String FORM = "form";

    private DAOITweet daoITweet;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReTweet() {
        super();

    }

    public void init() throws ServletException {
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**Préparation de l'objet formulaire*/
        SendTweetForm form = new SendTweetForm(daoITweet);

        /**Appel au traitement et à la validation de la requête, et récupération du bean en résultant*/
        ReTweet reTweet = form.sendReTweet(request);

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(RETWEET, reTweet);
        request.setAttribute(FORM, form);
        response.sendRedirect(HOMEPAGE);
        //this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
    }

}
