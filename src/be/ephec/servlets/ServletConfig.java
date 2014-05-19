package be.ephec.servlets;

import javax.servlet.http.HttpServlet;

/**
 * Created by michaelsilvestre on 18/05/14.
 */
public abstract class ServletConfig extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String USER_SESSION = "userSession";
    protected static final long serialVersionUID = 1L;

    public ServletConfig() {
        super();
    }
}
