package be.ephec.utilities;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public class Validator {


    public static void mailValidation(String mail) throws Exception {
        if (mail != null) {
            throw new Exception("Fuck u bot!");
        }
    }

    /**
     * Email validation
     */
    public static void emailValidation(String email) throws Exception {

        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Please enter a valide email.");
            }
        } else {
            throw new Exception("Please enter your email.");
        }
    }

    /**
     * Password validation
     */
    public static void passwordValidation(String password, String confirmation) throws Exception {
        if (password != null && password.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!password.equals(confirmation)) {
                throw new Exception("Password doesn't match confirmation.");
            } else if (password.trim().length() < 3) {
                throw new Exception("Passwords must contain at least 3 characters.");
            }
        } else {
            throw new Exception("Please enter and confirm your password.");
        }
    }

    /**
     * User name validation
     */
    public static void userNameValidation(String userName) throws Exception {
        if (userName != null && userName.trim().length() < 3) {
            throw new Exception("The username must contain at least 3 characters.");
        }
    }

    public static void firstNameValidation(String firstName) throws Exception {
        if (firstName != null && firstName.trim().length() < 3) {
            throw new Exception("The username must contain at least 3 characters.");
        }
    }

    public static void lastNameValidation(String lastName) throws Exception {
        if (lastName != null && lastName.trim().length() < 3) {
            throw new Exception("The username must contain at least 3 characters.");
        }
    }
}
