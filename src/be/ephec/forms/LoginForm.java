package be.ephec.forms;

import be.ephec.beans.User;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOUser;
import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.UserTools;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ValidationForm {

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";


    private DAOUser daoUser;

    private String result;

    public LoginForm(DAOIUser daoIUser) {
        this.daoUser = (DAOUser) daoIUser;
    }

    public String getResult() {
        return result;
    }


    public User loginValidation(HttpServletRequest request) {
        User user = null;
        Boolean bEmptyFieldError = false;
        String userName;
        String password;

        if ((userName = FrameworkSupport.getTrimedValue(request, USERNAME)).isEmpty()) {
            bEmptyFieldError = true;
            setErreur(USERNAME, "empty username");
        }
        if ((password = FrameworkSupport.getTrimedValue(request, PASSWORD)).isEmpty()) {
            bEmptyFieldError = true;
            setErreur(PASSWORD, "empty password");
        }
        if (bEmptyFieldError) {
            return user;
        }

        user = daoUser.loginSearch(userName, UserTools.sha256(password));

        if (user == null) {
            setErreur(USERNAME, "Invalid username.");
            setErreur(PASSWORD, "Invalid password");
        } else {
            result = "Login SuccessFull";
        }

        return user;
    }

}
