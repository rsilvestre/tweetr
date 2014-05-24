package be.ephec.exceptions;

public class ValidatorException extends RuntimeException {

    public ValidatorException(String body) {
        super(body);
    }

    public ValidatorException(String body, Throwable cause) {
        super(body, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
