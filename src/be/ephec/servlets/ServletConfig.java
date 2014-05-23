package be.ephec.servlets;

import javax.servlet.http.HttpServlet;

public abstract class ServletConfig extends HttpServlet {
    protected static final String CONF_DAO_FACTORY = "daofactory";
    protected static final String USER_SESSION = "userSession";
    protected static final String DASHBOARD = "dashboard";
    protected static final long serialVersionUID = 1L;
    protected static final String ERROR = "error";

    public ServletConfig() {
        super();
    }

}
