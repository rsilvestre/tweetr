package com.ephec.forms;

import com.ephec.beans.User;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utilities.FrameworkSupport;
import com.ephec.utilities.UserTools;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginForm {

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";

    private Map<String, String> erreurs = new HashMap<String, String>();

    private DAOUser daoUser;

    private String result;

    public LoginForm(DAOIUser daoIUser) {
        this.daoUser = (DAOUser) daoIUser;
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public User loginValidation(HttpServletRequest request) {
        User user = null;
        Boolean bEmptyFieldError = false;
        String userName;
        String password;

        if ((userName = FrameworkSupport.getTrimedValue(request, USERNAME)).isEmpty()) {
            bEmptyFieldError = true;
            erreurs.put(USERNAME, "empty username");
        }
        if ((password = FrameworkSupport.getTrimedValue(request, PASSWORD)).isEmpty()) {
            bEmptyFieldError = true;
            erreurs.put(PASSWORD, "empty password");
        }
        if (bEmptyFieldError) {
            return user;
        }

        user = daoUser.loginSearch(userName, UserTools.sha256(password));

        if (user == null) {
            erreurs.put(USERNAME, "Invalid username.");
            erreurs.put(PASSWORD, "Invalid password");
        } else {
            result = "Login SuccessFull";
        }

        return user;
    }

}
