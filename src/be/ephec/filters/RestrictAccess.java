package be.ephec.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterSession", urlPatterns = "/*")
public class RestrictAccess implements Filter {
    private static final String USER_SESSION = "userSession";
    private static final String ERROR = "error";
    private static final String PAGE_ERROR = "/Error";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");

		/* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

		/* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring(
                request.getContextPath().length());
        if (chemin.startsWith("/assets") || chemin.startsWith("/Images")) {
            chain.doFilter(request, response);
            return;
        }

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        //PageOut.fromString(request.getRequestURI().substring(request.getContextPath().length()));
        String subRequestURI;
        if (session.getAttribute(USER_SESSION) == null && PageOut.fromString(subRequestURI = request.getRequestURI().substring(request.getContextPath().length())) != null) {
            if (PageOut.ROOT == PageOut.fromString(subRequestURI)) {
                request.getRequestDispatcher(PageOut.HOME.toString()).forward(request, response);
            } else {
                request.getRequestDispatcher(subRequestURI).forward(request, response);
            }
        } else if (session.getAttribute(USER_SESSION) != null && PageIn.fromString(subRequestURI = request.getRequestURI().substring(request.getContextPath().length())) != null) {
            if (PageIn.ROOT == PageIn.fromString(subRequestURI)) {
                request.getRequestDispatcher(PageIn.HOMEPAGE.toString()).forward(request, response);
            } else {
                request.getRequestDispatcher(subRequestURI).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, "La page " + request.getRequestURI().substring(request.getContextPath().length()) + " n'existe pas");
            request.getRequestDispatcher(PAGE_ERROR).forward(request, response);
            /* Affichage de la page restreinte */
            //chain.doFilter(request, response);
        }
        /*
        if (session.getAttribute(USER_SESSION) == null
                && !"/Home".equals(request.getRequestURI().substring(request.getContextPath().length()))
                && !"/About".equals(request.getRequestURI().substring(request.getContextPath().length()))
                && !"/Login".equals(request.getRequestURI().substring(request.getContextPath().length()))
                && !"/CreateAccount".equals(request.getRequestURI().substring(request.getContextPath().length()))
                ) {

            System.out.println("session.getAttribute(USER_SESSION) == null: ");
            System.out.println(session.getAttribute(USER_SESSION) == null);
            *//* Redirection vers la page publique *//*
            if (!"/Home".equals(request.getRequestURI().substring(request.getContextPath().length()))) {
                request.getRequestDispatcher(HOME).forward(request, response);
            } else if (!"/About".equals(request.getRequestURI().substring(request.getContextPath().length()))) {
                request.getRequestDispatcher(HOME).forward(request, response);
            } else {
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        } else if (session.getAttribute(USER_SESSION) != null
                && "/".equals(request.getRequestURI().substring(request.getContextPath().length()))
                ) {
            request.getRequestDispatcher(HOMEPAGE).forward(request, response);
        } else {
            *//* Affichage de la page restreinte *//*
            chain.doFilter(request, response);
        }*/

    }

    public void destroy() {
    }

    public enum PageIn {
        ROOT("/"),
        HOMEPAGE("/HomePage"),
        ABOUT("/About"),
        DELETEACCOUNT("/DeleteAccount"),
        MODIFYACCOUNT("/ModifyAccount"),
        MODIFYACCOUNTINFO("/ModifyAccountInfo"),
        MODIFYACCOUNTIMAGE("/ModifyAccountImage"),
        SHOWACCOUNT("/ShowAccount"),
        LOGOUT("/Logout"),
        EDITPROFILE("/EditProfile"),
        USER("/User"),
        FOLLOWER("/Follower"),
        FOLLOWING("/Following"),
        SENDRETWEET("/SendReTweet"),
        SENDTWEET("/SendTweet"),
        TWEET("/Tweet"),
        RECHERCHE("/Recherche");

        private String converter;

        PageIn(String c) {
            this.converter = c;
        }

        public static PageIn fromString(String text) {
            if (text != null) {
                for (PageIn b : PageIn.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return b;
                    }
                }
            }
            return null;
        }

        public String toString() {
            return converter;
        }
    }

    public enum PageOut {
        ROOT("/"),
        HOME("/Home"),
        ABOUT("/About"),
        CREATEACCOUNT("/CreateAccount"),
        LOGIN("/Login");

        private String converter;

        PageOut(String c) {
            this.converter = c;
        }

        public static PageOut fromString(String text) {
            if (text != null) {
                for (PageOut b : PageOut.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return b;
                    }
                }
            }
            return null;
        }

        public String toString() {
            return converter;
        }
    }
}
