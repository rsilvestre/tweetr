package com.ephec.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterSession", urlPatterns = "/*")
public class RestrictAccess implements Filter {

    public static final String LOGIN = "/Login";
    public static final String USER_SESSION = "userSession";

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

		/* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

		/* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring(
                request.getContextPath().length());
        if (chemin.startsWith("/Bootstrap/css")) {
            chain.doFilter(request, response);
            return;
        }

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if (session.getAttribute(USER_SESSION) == null
                && !"/Login".equals(request.getRequestURI().substring(request.getContextPath().length()))
                && !"/CreateAccount".equals(request.getRequestURI().substring(request.getContextPath().length()))) {

            System.out.println("session.getAttribute(USER_SESSION) == null: ");
            System.out.println(session.getAttribute(USER_SESSION) == null);
            /* Redirection vers la page publique */
            request.getRequestDispatcher(LOGIN).forward(request, response);

        } else {
            /* Affichage de la page restreinte */
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }
}
