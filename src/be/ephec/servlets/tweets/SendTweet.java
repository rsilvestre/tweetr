package be.ephec.servlets.tweets;

import be.ephec.beans.TweetIn;
import be.ephec.dao.DAOFactory;
import be.ephec.dao.DAOITweet;
import be.ephec.forms.SendTweetForm;
import be.ephec.servlets.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class SendTwit
 */
@WebServlet("/SendTweet")
public class SendTweet extends ServletConfig {
    public static final String HOMEPAGE = "/WEB-INF/homePage.jsp";
    //public static final String HOMEPAGE = "/HomePage";
    public static final String TWEET = "tweet";
    public static final String FORM = "form";

    private DAOITweet daoITweet;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTweet() {
        super();
    }

    public void init() throws ServletException {
        //Récupération d'une instance de notre DAO Utilisateur
        this.daoITweet = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getTweetDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		/**Display createAccount.jsp*/
//		this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        /**Préparation de l'objet formulaire*/
        SendTweetForm form = new SendTweetForm(daoITweet);

        /**Appel au traitement et à la validation de la requête, et récupération du bean en résultant*/
        TweetIn tweet = form.createTweet(request);

        /**Stockage du formulaire et du bean dans l'objet request*/
        request.setAttribute(TWEET, tweet);
        request.setAttribute(FORM, form);
        //response.sendRedirect(HOMEPAGE);
        this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);
    }

}
