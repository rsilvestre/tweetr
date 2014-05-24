package be.ephec.utilities;

import be.ephec.exceptions.ValidatorException;

public class Validator {
    private static final int TWEET_SIZE_MIN = 3;
    private static final int TWEET_SIZE_MAX = 140;

    public static void emailValidation(String email) throws ValidatorException {

        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new ValidatorException("Please enter a valide email.");
            }
        } else {
            throw new ValidatorException("Please enter your email.");
        }
    }

    public static void passwordValidation(String password, String confirmation) throws ValidatorException {
        if (password != null && password.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!password.equals(confirmation)) {
                throw new ValidatorException("Password doesn't match confirmation.");
            } else if (password.trim().length() < 3) {
                throw new ValidatorException("Passwords must contain at least 3 characters.");
            }
        } else {
            throw new ValidatorException("Please enter and confirm your password.");
        }
    }

    public static void userNameValidation(String userName) throws ValidatorException {
        if (userName != null && userName.trim().length() < 3) {
            throw new ValidatorException("The username must contain at least 3 characters.");
        }
    }

    public static void firstNameValidation(String firstName) throws ValidatorException {
        if (firstName != null && firstName.trim().length() < 3) {
            throw new ValidatorException("The username must contain at least 3 characters.");
        }
    }

    public static void lastNameValidation(String lastName) throws ValidatorException {
        if (lastName != null && lastName.trim().length() < 3) {
            throw new ValidatorException("The username must contain at least 3 characters.");
        }
    }

    public static void bodyValidation(String body) throws ValidatorException {
        if (body == null || body.trim().length() < TWEET_SIZE_MIN) {
            throw new ValidatorException("The tweet must contain at least 3 characters.");
        }

        if (body.length() > TWEET_SIZE_MAX) {
            throw new ValidatorException("The tweet is too long.");
        }
    }
}
