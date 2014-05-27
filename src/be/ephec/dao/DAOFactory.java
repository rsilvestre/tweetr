package be.ephec.dao;

import be.ephec.exceptions.DAOConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String PROPERTIES_FILE = "/be/ephec/dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";

    private final String url;
    private final Properties properties;

    public DAOFactory(String url, Properties properties) {
        this.url = url;
        this.properties = properties;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException("The properties file" + PROPERTIES_FILE + " is not found.");
        }

        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException("Unable to load the properties file" + PROPERTIES_FILE, e);
        }

        try {
            Class.forName(properties.getProperty(PROPERTY_DRIVER));
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException(
                    "Le driver est introuvable dans le classpath.", e);
        }

        return new DAOFactory(properties.getProperty(PROPERTY_URL), properties);
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }

    public DAOIUser getUserDao() {
        return new DAOUser(this);
    }

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
