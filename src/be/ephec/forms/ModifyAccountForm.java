package be.ephec.forms;

import be.ephec.beans.User;
import be.ephec.dao.DAOFile;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIUser;
import be.ephec.dao.DAOUser;
import be.ephec.utilities.DirectoryTools;
import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.UserTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.URISyntaxException;

public class ModifyAccountForm extends ValidationForm {
    private static final String USERNAME = "userName";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String OLDPASSWORD = "oldpassword";
    private static final String NEWPASSWORD = "password";
    private static final String CONFIRMATION = "confirmation";
    private static final String IMAGE = "image";
    private static final String PATH = Thread.currentThread().getContextClassLoader().getResource("../../Images").getPath();

    private final DAOUser daoUser;
    private final DAOFile daoFile;


    public ModifyAccountForm(DAOIUser daoIUser, DAOIFile daoIFile) {
        super();
        this.daoUser = (DAOUser) daoIUser;
        this.daoFile = (DAOFile) daoIFile;
        //PATH = Thread.currentThread().getContextClassLoader().getResource("../../Images").toString();
    }

    public User modifyAccountInfo(HttpServletRequest request) {
        User oldUser = (User) request.getSession().getAttribute(USER_SESSION);
        User user = new User();

        user.setUserId(oldUser.getUserId());

        user.setUserName(validateData(request, USERNAME,
                (dataKey, erreursRef1) -> {
                    String newUsername = FrameworkSupport.getTrimedValue(request, dataKey);
                    if (!oldUser.getUserName().equals(newUsername) && daoUser.searchByUserName(newUsername) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(request, FIRSTNAME, null));
        user.setLastName(validateData(request, LASTNAME, null));
        user.setEmail(validateData(request, EMAIL,
                (dataKey, erreursRef1) -> {
                    String oldEmail = FrameworkSupport.getTrimedValue(request, dataKey);
                    if (!oldUser.getEmail().equals(oldEmail) && daoUser.searchByEmail(oldEmail) != null) {
                        erreursRef1.put(EMAIL, "This email is already related to an account.");
                    }
                }
        ));

        String oldPassword = FrameworkSupport.getTrimedValue(request, OLDPASSWORD);

        if (oldPassword.isEmpty()) {
            user.setPassword(oldUser.getPassword());
        } else {
            validateData(request, NEWPASSWORD,
                    (dataKey, erreursRef1) -> {
                        if (!oldUser.getPassword().equals(UserTools.sha256(oldPassword))) {
                            erreursRef1.put(OLDPASSWORD, "Invalid password.");
                        }
                        user.setPassword(UserTools.sha256(FrameworkSupport.getTrimedValue(request, dataKey)));
                    }
                    , CONFIRMATION
            );
        }

        user.setImage(oldUser.getImage());

        if (getErreurs().isEmpty()) {
            setResult("Your account has been modified successfully.");
            daoUser.update(user);
            return user;
        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return oldUser;
        }

    }

    public User modifyAccountImage(HttpServletRequest request) {
        String outputFilename = "";
        Part part = imageValidation(request);
        User user = (User) request.getSession().getAttribute(USER_SESSION);

        if (part != null) {
            try {
                outputFilename = "U" + user.getUserId().toString() + "-" + DirectoryTools.getFilename(part);
                daoFile.writeFile(part, outputFilename, PATH);
            } catch (IOException e) {
                setErreur(IMAGE, e.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        if (getErreurs().isEmpty()) {
            setResult("Your account has been modified successfully.");
            user.setImage(outputFilename);
            daoUser.update(user);
            return user;

        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return user;
        }
    }

    private Part imageValidation(HttpServletRequest request) {
        try {
            return request.getPart(IMAGE);
        } catch (IllegalStateException | IOException | ServletException e) {
            setErreur(IMAGE, e.getMessage());
        }
        return null;

    }
}
