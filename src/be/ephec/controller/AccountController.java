package be.ephec.controller;

import be.ephec.beans.User;
import be.ephec.dao.DAOIFile;
import be.ephec.dao.DAOIFollow;
import be.ephec.dao.DAOITweet;
import be.ephec.dao.DAOIUser;
import be.ephec.filters.RestrictAccess;
import be.ephec.servlets.accounts.ModifyAccount;
import be.ephec.utilities.DirectoryTools;
import be.ephec.utilities.FrameworkSupport;
import be.ephec.utilities.UserTools;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class AccountController extends ApplicationController {
    protected static final String DASHBOARD = "dashboard";
    private static final String CREATEACCOUNT = "/WEB-INF/createAccount.jsp";
    private static final String LOGIN = "/WEB-INF/login.jsp";
    private static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    private static final String TWEETS = "tweets";
    private static final String SHOWACCOUNT = "/WEB-INF/showAccount.jsp";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "ShowAccount";
    private static final String USER = "user";

    private static final String PASSWORD = "password";

    private static final String USERNAME = "userName";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String EMAIL = "email";
    private static final String OLDPASSWORD = "oldpassword";
    private static final String NEWPASSWORD = "password";
    private static final String CONFIRMATION = "confirmation";

    private static final String IMAGE = "image";
    private static final String PATH = Thread.currentThread().getContextClassLoader().getResource("../../Images").getPath();

    public AccountController(GenericServlet servlet, HttpServletRequest request, HttpServletResponse response) {
        super(servlet, request, response);
    }

    public void Modify(Object... objects) throws ServletException, IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];

        HttpSession session = this.getRequest().getSession();

        User user = this.modifyAccountInfo(daoIUser);

        if (this.getErreurs().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        }

        this.getRequest().setAttribute(USER, user);

        if (this.getErreurs().isEmpty()) {
            this.getResponse().sendRedirect(RestrictAccess.PageIn.SHOWACCOUNT.toString());
        } else {
            this.getServlet().getServletContext().getRequestDispatcher(ModifyAccount.MODIFYACCOUNT).forward(this.getRequest(), this.getResponse());
        }
    }

    public void Create(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        User user = this.createUserAccount(daoIUser);

        if (user == null) {
            this.getResponse().sendRedirect(null);
        }
        if (this.getErreurs().isEmpty()) {
            this.getRequest().getSession().setAttribute(USER_SESSION, user);
        } else {
            this.getRequest().getSession().setAttribute(USER_SESSION, null);
        }

        if (this.getErreurs().isEmpty()) {
            this.getResponse().sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            this.getServlet().getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(this.getRequest(), this.getResponse());
        }
    }

    public void Delete(Object... objects) throws IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOITweet daoITweet = (DAOITweet) objects[1];
        DAOIFollow daoIFollow = (DAOIFollow) objects[2];
        this.deleteAccount(daoIUser, daoITweet, daoIFollow);
        this.getResponse().sendRedirect(RestrictAccess.PageIn.LOGOUT.toString());
    }

    public void Login(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];

        User user = this.loginValidation(daoIUser);

        this.getRequest().setAttribute(USER, user);

        if (this.getErreurs().isEmpty()) {

            this.getRequest().getSession().setAttribute(USER_SESSION, user);

            Cookie cookie = null;
            Cookie[] cookies = this.getRequest().getCookies();

            if (cookies != null) {

                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals(USERNAME)) {

                        cookie = cooky;
                        if (!(cookie.getValue().equals(user.getUserName()))) {
                            cookie.setValue(user.getUserName());
                            this.getResponse().addCookie(cookie);
                        }
                    }
                }
            }

            if (cookie == null) {

                cookie = new Cookie(USERNAME, user.getUserName());
                cookie.setPath(this.getRequest().getContextPath());
                this.getResponse().addCookie(cookie);
            }
            this.getResponse().sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            this.getRequest().getSession().setAttribute(USER_SESSION, null);
            this.getServlet().getServletContext().getRequestDispatcher(LOGIN).forward(this.getRequest(), this.getResponse());
        }
    }

    public void Image(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOIFile daoIFile = (DAOIFile) objects[1];

        User user = this.modifyAccountImage(daoIUser, daoIFile);

        if (this.getErreurs().isEmpty()) {
            this.getRequest().getSession().setAttribute(USER_SESSION, user);
        }

        if (this.getErreurs().isEmpty()) {
            this.getResponse().sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            this.getServlet().getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(this.getRequest(), this.getResponse());
        }
    }

    public void Show(Object... objects) throws ServletException, IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOITweet daoITweet = (DAOITweet) objects[1];
        DAOIFollow daoIFollow = (DAOIFollow) objects[2];

        SearchController formFlow = new SearchController(daoIUser, daoIFollow);

        formFlow.createFollow(this.getRequest());
        formFlow.deleteFollow(this.getRequest());

        String userId;
        User user;
        if ((userId = this.getRequest().getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(userId), (User) this.getRequest().getSession().getAttribute(USER_SESSION))) == null) {
            user = (User) this.getRequest().getSession().getAttribute(USER_SESSION);
        }

        HomepageController formShowAccount = new HomepageController(daoITweet, daoIUser);

        this.getRequest().setAttribute("user", user);
        this.getRequest().setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        this.getRequest().setAttribute(DASHBOARD, formShowAccount.getDashboardParams(user));
        this.getRequest().setAttribute(TWEETS, formShowAccount.getTweetOutList(user));
        this.getServlet().getServletContext().getRequestDispatcher(SHOWACCOUNT).forward(this.getRequest(), this.getResponse());
    }

    /**
     * Private method
     */


    private User modifyAccountInfo(DAOIUser daoIUser) {
        User oldUser = (User) this.getRequest().getSession().getAttribute(USER_SESSION);
        User user = new User();

        user.setUserId(oldUser.getUserId());

        user.setUserName(validateData(this.getRequest(), USERNAME,
                (dataKey, erreursRef1) -> {
                    String newUsername = FrameworkSupport.getTrimedValue(this.getRequest(), dataKey);
                    if (!oldUser.getUserName().equals(newUsername) && daoIUser.searchByUserName(newUsername) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(this.getRequest(), FIRSTNAME, null));
        user.setLastName(validateData(this.getRequest(), LASTNAME, null));
        user.setEmail(validateData(this.getRequest(), EMAIL,
                (dataKey, erreursRef1) -> {
                    String oldEmail = FrameworkSupport.getTrimedValue(this.getRequest(), dataKey);
                    if (!oldUser.getEmail().equals(oldEmail) && daoIUser.searchByEmail(oldEmail) != null) {
                        erreursRef1.put(EMAIL, "This email is already related to an account.");
                    }
                }
        ));

        String oldPassword = FrameworkSupport.getTrimedValue(this.getRequest(), OLDPASSWORD);

        if (oldPassword.isEmpty()) {
            user.setPassword(oldUser.getPassword());
        } else {
            validateData(this.getRequest(), NEWPASSWORD,
                    (dataKey, erreursRef1) -> {
                        if (!oldUser.getPassword().equals(UserTools.sha256(oldPassword))) {
                            erreursRef1.put(OLDPASSWORD, "Invalid password.");
                        }
                        user.setPassword(UserTools.sha256(FrameworkSupport.getTrimedValue(this.getRequest(), dataKey)));
                    }
                    , CONFIRMATION
            );
        }

        user.setImage(oldUser.getImage());

        if (getErreurs().isEmpty()) {
            setResult("Your account has been modified successfully.");
            daoIUser.update(user);
            return user;
        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return oldUser;
        }
    }

    private User createUserAccount(DAOIUser daoIUser) {

        User user = new User();

        user.setUserName(validateData(this.getRequest(), USERNAME,
                (dataKey, erreursRef1) -> {
                    if (daoIUser.searchByUserName(FrameworkSupport.getTrimedValue(this.getRequest(), dataKey)) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(this.getRequest(), FIRSTNAME, null));
        user.setLastName(validateData(this.getRequest(), LASTNAME, null));
        user.setEmail(validateData(this.getRequest(), EMAIL,
                (dataKey, erreursRef1) -> {
                    if (daoIUser.searchByEmail(FrameworkSupport.getTrimedValue(this.getRequest(), dataKey)) != null) {
                        erreursRef1.put(dataKey, "This email is already related to an account.");
                    }
                }
        ));
        validateData(this.getRequest(), PASSWORD,
                (dataKey, erreursRef1) -> user.setPassword(UserTools.sha256(FrameworkSupport.getTrimedValue(this.getRequest(), dataKey)))
                , CONFIRMATION);

        user.setImage("0");

        if (getErreurs().isEmpty()) {
            setResult("Your account has been created successfully.");
            daoIUser.create(user);
        } else {
            setResult("Your account has not been created, please verify the entered information...");
        }

        return user;
    }

    private void deleteAccount(DAOIUser daIUser, DAOITweet daoITweet, DAOIFollow daoIFollow) {
        int userId = ((User) this.getRequest().getSession().getAttribute(USER_SESSION)).getUserId();

        daoITweet.deleteReTweet(userId);
        daoITweet.deleteTweet(userId);
        daoIFollow.deleteUserFollower(userId);
        daoIFollow.deleteUserFollowing(userId);
        daIUser.delete(userId);
    }

    private User loginValidation(DAOIUser daoIUser) {
        User user;
        Boolean bEmptyFieldError = false;
        String userName;
        String password;
        if (null != (user = (User) this.getRequest().getSession().getAttribute(USER_SESSION))) {
            return user;
        }

        if ((userName = FrameworkSupport.getTrimedValue(this.getRequest(), USERNAME)).isEmpty()) {
            bEmptyFieldError = true;
            setErreur(USERNAME, "empty username");
        }
        if ((password = FrameworkSupport.getTrimedValue(this.getRequest(), PASSWORD)).isEmpty()) {
            bEmptyFieldError = true;
            setErreur(PASSWORD, "empty password");
        }
        if (bEmptyFieldError) {
            return null;
        }

        user = daoIUser.loginSearch(userName, UserTools.sha256(password));

        if (user == null) {
            setErreur(USERNAME, "Invalid username.");
            setErreur(PASSWORD, "Invalid password");
        }

        return user;
    }

    private User modifyAccountImage(DAOIUser daoIUser, DAOIFile daoIFile) {
        String outputFilename = "";
        Part part = imageValidation();
        User user = (User) this.getRequest().getSession().getAttribute(USER_SESSION);

        if (part != null) {
            try {
                outputFilename = "U" + user.getUserId().toString() + "-" + DirectoryTools.getFilename(part);
                daoIFile.writeFile(part, outputFilename, PATH);
            } catch (IOException e) {
                setErreur(IMAGE, e.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        if (getErreurs().isEmpty()) {
            setResult("Your account has been modified successfully.");
            user.setImage(outputFilename);
            daoIUser.update(user);
            return user;

        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return user;
        }
    }

    private Part imageValidation() {
        try {
            return this.getRequest().getPart(IMAGE);
        } catch (IllegalStateException | IOException | ServletException e) {
            setErreur(IMAGE, e.getMessage());
        }
        return null;

    }
}
