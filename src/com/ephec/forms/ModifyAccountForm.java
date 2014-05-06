package com.ephec.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ephec.beans.User;
import com.ephec.dao.DAOFile;
import com.ephec.dao.DAOIFile;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utility.UserUtility;

public class ModifyAccountForm {
    private static final String USERNAME = "userName";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String OLDPASSWORD = "oldPassword";
    private static final String NEWPASSWORD = "newPassword";
    private static final String CONFIRMATION = "confirmation";
    private static final String PROFILEIMAGE = "profileImage";
    private static final String PATH = "C:/Users/BadUser/Documents/Workspace/Java/EphecTweetr/WebContent/Images";

    private static final String USER_SESSION = "userSession";

    private String result;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private DAOUser daoUser;
    private DAOFile daoFile;
    private User user;


    public ModifyAccountForm(DAOIUser daoIUser, DAOIFile daoIFile) {
        super();
        this.daoUser = (DAOUser) daoIUser;
        this.daoFile = (DAOFile) daoIFile;
    }

    public User modifyAccountInfo(HttpServletRequest request) {

        String userName = UserUtility.getFieldValue(request, USERNAME);
        String firstName = UserUtility.getFieldValue(request, FIRSTNAME);
        String lastName = UserUtility.getFieldValue(request, LASTNAME);
        String email = UserUtility.getFieldValue(request, EMAIL);
        String oldPassword = UserUtility.getFieldValue(request, OLDPASSWORD);
        String newPassword = UserUtility.getFieldValue(request, NEWPASSWORD);
        String confirmation = UserUtility.getFieldValue(request, CONFIRMATION);

        System.out.println(email);
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute(USER_SESSION);
        User user = new User();

        user.setUserId(oldUser.getUserId());
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setProfileImage(oldUser.getProfileImage());

        // Data validation
        try {
            if (!(oldUser.getUserName().equals(user.getUserName()))) {
                userNameValidation(userName);
            }
        } catch (Exception e) {
            setErreur(USERNAME, e.getMessage());
        }
        try {
            if (!(oldUser.getFirstName().equals(user.getFirstName()))) {
                firstNameValidation(firstName);
            }
        } catch (Exception e) {
            setErreur(FIRSTNAME, e.getMessage());
        }
        try {
            if (!(oldUser.getLastName().equals(user.getLastName()))) {
                lastNameValidation(lastName);
            }
        } catch (Exception e) {
            setErreur(LASTNAME, e.getMessage());
        }

        try {
            if (!(oldUser.getEmail().equals(user.getEmail()))) {
                emailValidation(email);
            }
        } catch (Exception e) {
            setErreur(EMAIL, e.getMessage());
        }

        try {

            if (oldPassword == null) {
                user.setPassword(oldUser.getPassword());
            } else {
                if (oldPassword == oldUser.getPassword()) {

                    if (passwordValidation(newPassword, confirmation)) {
                        user.setPassword(newPassword);
                    }

                } else {
                    setErreur(OLDPASSWORD, "Invalid password.");
                }
            }

        } catch (Exception e) {
            setErreur(NEWPASSWORD, e.getMessage());
        }

        if (erreurs.isEmpty()) {
            setResult("Your account has been modified successfully.");
            daoUser.update(user);
            return user;


        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return oldUser;
        }

    }

    public User modifyAccountImage(HttpServletRequest request) {

        Part part = profileImageValidation(request);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_SESSION);

        if (part != null) {
            try {
                daoFile.writeFile(part, user.getUserId().toString(), PATH);
            } catch (IOException e) {
                setErreur(PROFILEIMAGE, e.getMessage());
            }
        }
        if (erreurs.isEmpty()) {
            setResult("Your account has been modified successfully.");
            user.setProfileImage(user.getUserId().toString());
            daoUser.update(user);
            return user;


        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return user;
        }
    }

    /**
     * Email validation
     */
    private void emailValidation(String email) throws Exception {
        if (email != null && email.trim().length() != 0) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Please enter a valide email.");
            }
        } else {
            throw new Exception("Please enter your email.");
        }

        user = daoUser.searchByEmail(email);

        if (user != null) {
            throw new Exception("This email is already related to an account.");
        }
    }

    /**
     * Password validation
     */
    private boolean passwordValidation(String password, String confirmation)
            throws Exception {
        if (password != null && password.trim().length() != 0
                && confirmation != null && confirmation.trim().length() != 0) {
            if (!password.equals(confirmation)) {
                throw new Exception("Password doesn't match confirmation.");
            } else if (password.trim().length() < 3) {
                throw new Exception(
                        "Passwords must contain at least 3 characters.");
            } else
                return true;
        } else {
            throw new Exception("Please enter and confirm your password.");

        }
    }

    /**
     * User name validation
     */
    private void userNameValidation(String userName) throws Exception {
        if (userName != null && userName.trim().length() < 3) {
            throw new Exception(
                    "The username must contain at least 3 characters.");
        }
        user = daoUser.searchByUserName(userName);

        if (user != null) {
            throw new Exception("The user name already exist.");
        }
    }

    private void firstNameValidation(String firstName) throws Exception {
        if (firstName != null && firstName.trim().length() < 3) {
            throw new Exception(
                    "The username must contain at least 3 characters.");
        }
    }

    private void lastNameValidation(String lastName) throws Exception {
        if (lastName != null && lastName.trim().length() < 3) {
            throw new Exception(
                    "The username must contain at least 3 characters.");
        }
    }

    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private Part profileImageValidation(HttpServletRequest request) {
        try {
            Part part = request.getPart(PROFILEIMAGE);
            return part;
        } catch (IllegalStateException e) {
            setErreur(PROFILEIMAGE, e.getMessage());
        } catch (IOException e) {
            setErreur(PROFILEIMAGE, e.getMessage());
        } catch (ServletException e) {
            setErreur(PROFILEIMAGE, e.getMessage());
        }
        return null;

    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
