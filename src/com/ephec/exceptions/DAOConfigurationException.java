package com.ephec.exceptions;

public class DAOConfigurationException extends RuntimeException {

    /*
     * Constructeurs
     */
    public DAOConfigurationException(String body) {
        super(body);
    }

    public DAOConfigurationException(String body, Throwable cause) {
        super(body, cause);
    }

    public DAOConfigurationException(Throwable cause) {
        super(cause);
    }
}
