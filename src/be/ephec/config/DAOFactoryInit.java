package be.ephec.config;

import be.ephec.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DAOFactoryInit implements ServletContextListener {

    public static final String ATT_DAO_FACTORY = "daofactory";

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute(ATT_DAO_FACTORY, DAOFactory.getInstance());
    }


}
