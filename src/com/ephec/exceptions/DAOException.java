package com.ephec.exceptions;

public class DAOException extends RuntimeException {

    /*
     * Constructeurs
     */
    public DAOException(String body) {
        super(body);
    }

    public DAOException(String body, Throwable cause) {
        super(body, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
