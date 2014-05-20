package be.ephec.forms;

import be.ephec.beans.User;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOUser;
import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.UserTools;

import javax.servlet.http.HttpServletRequest;

public final class CreateAccountForm extends ValidationForm {

    private static final String USERNAME = "userName";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIRMATION = "confirmation";

    private final DAOUser daoUser;

    public CreateAccountForm(DAOIUser daoIUser) {
        super();
        this.daoUser = (DAOUser) daoIUser;
    }

    // Retrieve the data entered by the user

    public User createUserAccount(HttpServletRequest request) {
        User user = new User();

        user.setUserName(validateData(request, USERNAME,
                (dataKey, erreursRef1) -> {
                    if (daoUser.searchByUserName(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(request, FIRSTNAME, null));
        user.setLastName(validateData(request, LASTNAME, null));
        user.setEmail(validateData(request, EMAIL,
                (dataKey, erreursRef1) -> {
                    if (daoUser.searchByEmail(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                        erreursRef1.put(dataKey, "This email is already related to an account.");
                    }
                }
        ));
        validateData(request, PASSWORD,
                (dataKey, erreursRef1) -> user.setPassword(UserTools.sha256(FrameworkSupport.getTrimedValue(request, dataKey)))
                , CONFIRMATION);

        user.setImage("0");

        if (getErreurs().isEmpty()) {
            setResult("Your account has been created successfully.");
            daoUser.create(user);
        } else {
            setResult("Your account has not been created, please verify the entered information...");
        }

        return user;
    }

}
