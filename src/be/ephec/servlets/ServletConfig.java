package be.ephec.servlets;

import be.ephec.filters.RestrictAccess;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ServletConfig extends HttpServlet {
    protected static final String CONF_DAO_FACTORY = "daofactory";
    protected static final String USER_SESSION = "userSession";
    protected static final String DASHBOARD = "dashboard";
    protected static final long serialVersionUID = 1L;
    protected static final String ERROR = "error";

    public ServletConfig() {
        super();
    }

    public void DynamicCallController(HttpServletRequest request, HttpServletResponse response, Object... object) throws ServletException, IOException {
        String subRequestURI = request.getRequestURI();
        subRequestURI = subRequestURI.substring(1, subRequestURI.length());
        Pattern pat = Pattern.compile("^([A-Z][a-z]+)([A-Z][a-z]+)$");
        Matcher match = pat.matcher(subRequestURI);
        match.find();
        String action = match.group(1), controller = match.group(2);
        if (action.isEmpty() || controller.isEmpty()) {
            request.setAttribute(ERROR, "La page " + request.getRequestURI().substring(request.getContextPath().length()) + " n'existe pas");
            request.getRequestDispatcher(RestrictAccess.PageInOut.ERROR.toString()).forward(request, response);
        }

        //Object toto = Thread.currentThread().getContextClassLoader().getResource("../../Images").toString();
        try {
            Class<?> clazz = Class.forName("be.ephec.controller." + controller + "Controller");
            Constructor<?> ctor = clazz.getConstructor(GenericServlet.class, HttpServletRequest.class, HttpServletResponse.class);
            Object objectz = ctor.newInstance(this, request, response);
            clazz.getMethod(action + "Action", Object[].class).invoke(objectz, new Object[]{object});
        } catch (InvocationTargetException ex) {
            request.setAttribute(ERROR, "Erreur dans le controlleur " + controller + ", d'action " + action + ". Détail : " + ex.getTargetException().getMessage());
            request.getRequestDispatcher(RestrictAccess.PageInOut.ERROR.toString()).forward(request, response);
        } catch (Exception ex) {
            //throw new Exception();
            request.setAttribute(ERROR, "Pas de controller " + controller + " ou d'action " + action + ". Détail : " + ex.getMessage());
            request.getRequestDispatcher(RestrictAccess.PageInOut.ERROR.toString()).forward(request, response);
        }
    }
}
