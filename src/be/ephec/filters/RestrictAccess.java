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

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String chemin = request.getRequestURI().substring(
                request.getContextPath().length());
        if (chemin.startsWith("/assets") || chemin.startsWith("/Images")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();

        String subRequestURI;
        if (session.getAttribute(USER_SESSION) == null && PageOut.containString(subRequestURI = request.getRequestURI().substring(request.getContextPath().length()))) {
            if (PageInOut.ROOT == PageInOut.fromString(subRequestURI)) {
                request.getRequestDispatcher(PageOut.HOME.toString()).forward(request, response);
            } else {
                request.getRequestDispatcher(subRequestURI).forward(request, response);
            }
        } else if (session.getAttribute(USER_SESSION) != null && PageIn.containString(subRequestURI = request.getRequestURI().substring(request.getContextPath().length()))) {
            if (PageInOut.ROOT == PageInOut.fromString(subRequestURI)) {
                request.getRequestDispatcher(PageIn.HOMEPAGE.toString()).forward(request, response);
            } else {
                request.getRequestDispatcher(subRequestURI).forward(request, response);
            }
        } else {
            request.setAttribute(ERROR, "La page " + request.getRequestURI().substring(request.getContextPath().length()) + " n'existe pas");
            request.getRequestDispatcher(PageInOut.ERROR.toString()).forward(request, response);
        }

    }

    public void destroy() {
    }

    public enum PageIn {
        HOMEPAGE("/Homepage"),
        DELETEACCOUNT("/DeleteAccount"),
        MODIFYACCOUNT("/ModifyAccount"),
        MODIFYACCOUNTIMAGE("/ImageAccount"),
        SHOWACCOUNT("/ShowAccount"),
        LOGOUT("/LogoutAccount"),
        EDITPROFILE("/EditProfile"),
        USER("/User"),
        FOLLOWER("/Follower"),
        FOLLOWING("/Following"),
        RETWEET("/RetweetMessage"),
        TWEET("/TweetMessage"),
        RECHERCHE("/Search");

        private final String converter;

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

        public static Boolean containString(String text) {
            if (text != null) {
                for (PageIn b : PageIn.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return true;
                    }
                }
            }
            return PageInOut.containString(text);
        }

        public String toString() {
            return converter;
        }
    }

    public enum PageOut {
        HOME("/Home"),
        CREATEACCOUNT("/CreateAccount"),
        LOGIN("/LoginAccount");

        private final String converter;

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

        public static Boolean containString(String text) {
            if (text != null) {
                for (PageOut b : PageOut.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return true;
                    }
                }
            }
            return PageInOut.containString(text);
        }

        public String toString() {
            return converter;
        }
    }

    public enum PageInOut {
        ROOT("/"),
        ERROR("/Error"),
        ABOUT("/About");

        private final String converter;

        PageInOut(String c) {
            this.converter = c;
        }

        public static PageInOut fromString(String text) {
            if (text != null) {
                for (PageInOut b : PageInOut.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return b;
                    }
                }
            }
            return null;
        }

        public static Boolean containString(String text) {
            if (text != null) {
                for (PageInOut b : PageInOut.values()) {
                    if (text.equalsIgnoreCase(b.converter)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String toString() {
            return converter;
        }
    }
}
