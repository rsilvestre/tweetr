package com.ephec.forms;

import com.ephec.beans.User;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utilities.FrameworkSupport;
import com.ephec.utilities.UserTools;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class CreateAccountForm extends ValidationForm {

    private static final String USERNAME = "userName";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String MAIL = "mail";
    private static final String PASSWORD = "password";
    private static final String CONFIRMATION = "confirmation";

    private String result;
    //private Map<String, String> erreurs = new HashMap<String, String>();
    private AtomicReference<Map<String, String>> erreursRef = new AtomicReference<Map<String, String>>();
    private DAOUser daoUser;
    private User user;

    public CreateAccountForm(DAOIUser daoIUser) {
        this.daoUser = (DAOUser) daoIUser;
        erreursRef.set(new HashMap<String, String>());
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getErreurs() {
        return erreursRef.get();
    }

    // Retrieve the data entered by the user

    public User createUserAccount(HttpServletRequest request) {

        User user = new User();


        user.setUserName(validateData(request, erreursRef, USERNAME,
                (dataKey, erreursRef1) -> {
                    if (daoUser.searchByUserName(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                        if (daoUser.searchByEmail(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                            Map<String, String> erreurTmp = erreursRef.get();
                            erreurTmp.put(dataKey, "The user name already exist.");
                            erreursRef1.set(erreurTmp);
                        }
                    }
                }
        ));
        user.setFirstName(validateData(request, erreursRef, FIRSTNAME, null));
        user.setLastName(validateData(request, erreursRef, LASTNAME, null));
        user.setEmail(validateData(request, erreursRef, EMAIL,
                (dataKey, erreursRef1) -> {
                    if (daoUser.searchByEmail(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                        Map<String, String> erreurTmp = erreursRef.get();
                        erreurTmp.put(dataKey, "This email is already related to an account.");
                        erreursRef1.set(erreurTmp);
                    }
                }
        ));
        validateData(request, erreursRef, PASSWORD,
                (dataKey, erreursRef1) -> user.setPassword(UserTools.sha256(FrameworkSupport.getTrimedValue(request, dataKey)))
                , CONFIRMATION);


        user.setImage("0");

        if (erreursRef.get().isEmpty()) {
            result = "Your account has been created successfully.";
            daoUser.create(user);

        } else {
            result = "Your account has not been created, please verify the entered information...";
        }

        return user;

    }

}
