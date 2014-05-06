package com.ephec.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.ephec.exceptions.DAOConfigurationException;

public class DAOFactory {

    private static final String PROPERTIES_FILE = "/com/ephec/dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "userName";
    private static final String PROPERTY_PASSWORD = "password";

    private String url;
    private String userName;
    private String password;

    DAOFactory(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    /**
     * This method is in charge of retrieving the DB connection information,
     * loading the JDBC driver and returning an instance of the Factory.
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String userName;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException("The properties file" + PROPERTIES_FILE + " is not found.");
        }

        try {
            properties.load(propertiesFile);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            userName = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
        } catch (IOException e) {
            throw new DAOConfigurationException("Unable to load the properties file" + PROPERTIES_FILE, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException(
                    "Le driver est introuvable dans le classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, userName, password);
        return instance;
    }

    /**
     * This method in charge of providing a connection to the DB.
     */
    Connection getConnection() throws SQLException {

        return DriverManager.getConnection(this.url, this.userName, this.password);
    }

    /**
     * Méthodes de récupération de l'implémentation des différents DAO
     * (only one for the moment)
     */
    public DAOIUser getUserDao() {

        return new DAOUser(this);
    }

    /**
     * Méthodes de récupération de l'implémentation des différents DAO
     * (only one for the moment)
     */
    public DAOITweet getTweetDao() {

        return new DAOTweet(this);
    }

    public DAOFollow getFollowDao() {

        return new DAOFollow(this);
    }

    public DAOFile getFileDao() {

        return new DAOFile(this);
    }
}
