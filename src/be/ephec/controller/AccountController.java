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

public class AccountController extends ValidationController {
    protected static final String DASHBOARD = "dashboard";
    private static final String CREATEACCOUNT = "/WEB-INF/createAccount.jsp";
    private static final String LOGIN = "/WEB-INF/login.jsp";
    private static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";
    private static final String TWEETS = "tweets";
    private static final String SHOWACCOUNT = "/WEB-INF/showAccount.jsp";
    private static final String RESPONSE_KEY = "response";
    private static final String RESPONSE_VALUE = "ShowAccount";
    private static final String USER = "user";
    private static final String FORM = "form";

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
    private final GenericServlet servlet;
    private final HttpServletRequest request;
    private final HttpServletResponse response;


    public AccountController(GenericServlet servlet, HttpServletRequest request, HttpServletResponse response) {
        super();
        this.servlet = servlet;
        this.request = request;
        this.response = response;
    }

    public void Modify(Object... objects) throws ServletException, IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        servlet.getServletContext().getRequestDispatcher(ModifyAccount.MODIFYACCOUNT).forward(request, response);

        HttpSession session = request.getSession();

        User user = this.modifyAccountInfo(daoIUser);

        if (this.getErreurs().isEmpty()) {
            session.setAttribute(USER_SESSION, user);
        }

        request.setAttribute(USER, user);

        if (this.getErreurs().isEmpty()) {
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            servlet.getServletContext().getRequestDispatcher(ModifyAccount.MODIFYACCOUNT).forward(request, response);
        }
    }

    public void Create(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        User user = this.createUserAccount(daoIUser);

        if (user == null) {
            response.sendRedirect(null);
        }
        if (this.getErreurs().isEmpty()) {
            request.getSession().setAttribute(USER_SESSION, user);
        } else {
            request.getSession().setAttribute(USER_SESSION, null);
        }

        if (this.getErreurs().isEmpty()) {
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            servlet.getServletContext().getRequestDispatcher(CREATEACCOUNT).forward(request, response);
        }
    }

    public void Delete(Object... objects) throws IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOITweet daoITweet = (DAOITweet) objects[1];
        DAOIFollow daoIFollow = (DAOIFollow) objects[2];
        this.deleteAccount(daoIUser, daoITweet, daoIFollow);
        response.sendRedirect(RestrictAccess.PageIn.LOGOUT.toString());
    }

    public void Login(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];

        LoginController form = new LoginController(daoIUser);

        User user = form.loginValidation(request);

        request.setAttribute(USER, user);
        request.setAttribute(FORM, form);

        if (form.getErreurs().isEmpty()) {

            request.getSession().setAttribute(USER_SESSION, user);

            Cookie cookie = null;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {

                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals(USERNAME)) {

                        cookie = cooky;
                        if (!(cookie.getValue().equals(user.getUserName()))) {
                            cookie.setValue(user.getUserName());
                            response.addCookie(cookie);
                        }
                    }
                }
            }

            if (cookie == null) {

                cookie = new Cookie(USERNAME, user.getUserName());
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            request.getSession().setAttribute(USER_SESSION, null);
            servlet.getServletContext().getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

    public void Image(Object... objects) throws IOException, ServletException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOIFile daoIFile = (DAOIFile) objects[1];

        User user = this.modifyAccountImage(daoIUser, daoIFile);

        if (this.getErreurs().isEmpty()) {
            request.getSession().setAttribute(USER_SESSION, user);
        }

        if (this.getErreurs().isEmpty()) {
            response.sendRedirect(RestrictAccess.PageIn.HOMEPAGE.toString());
        } else {
            servlet.getServletContext().getRequestDispatcher(MODIFYACCOUNT).forward(request, response);
        }
    }

    public void Show(Object... objects) throws ServletException, IOException {
        DAOIUser daoIUser = (DAOIUser) objects[0];
        DAOITweet daoITweet = (DAOITweet) objects[1];
        DAOIFollow daoIFollow = (DAOIFollow) objects[2];

        SearchController formFlow = new SearchController(daoIUser, daoIFollow);

        formFlow.createFollow(request);
        formFlow.deleteFollow(request);

        String userId;
        User user;
        if ((userId = request.getParameter("id")) == null || (user = daoIUser.searchById(Integer.parseInt(userId), (User) request.getSession().getAttribute(USER_SESSION))) == null) {
            user = (User) request.getSession().getAttribute(USER_SESSION);
        }

        HomePageController formShowAccount = new HomePageController(daoITweet, daoIUser);

        request.setAttribute("user", user);
        request.setAttribute(RESPONSE_KEY, RESPONSE_VALUE);
        request.setAttribute(DASHBOARD, formShowAccount.getDashboardParams(user));
        request.setAttribute(TWEETS, formShowAccount.getTweetOutList(user));
        servlet.getServletContext().getRequestDispatcher(SHOWACCOUNT).forward(request, response);
    }

    /**
     * Private method
     */


    private User modifyAccountInfo(DAOIUser daoIUser) {
        User oldUser = (User) request.getSession().getAttribute(USER_SESSION);
        User user = new User();

        user.setUserId(oldUser.getUserId());

        user.setUserName(validateData(request, USERNAME,
                (dataKey, erreursRef1) -> {
                    String newUsername = FrameworkSupport.getTrimedValue(request, dataKey);
                    if (!oldUser.getUserName().equals(newUsername) && daoIUser.searchByUserName(newUsername) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(request, FIRSTNAME, null));
        user.setLastName(validateData(request, LASTNAME, null));
        user.setEmail(validateData(request, EMAIL,
                (dataKey, erreursRef1) -> {
                    String oldEmail = FrameworkSupport.getTrimedValue(request, dataKey);
                    if (!oldUser.getEmail().equals(oldEmail) && daoIUser.searchByEmail(oldEmail) != null) {
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
            daoIUser.update(user);
            return user;
        } else {
            setResult("Your account has not been modified, please verify the entered information...");
            return oldUser;
        }
    }

    private User createUserAccount(DAOIUser daoIUser) {

        User user = new User();

        user.setUserName(validateData(request, USERNAME,
                (dataKey, erreursRef1) -> {
                    if (daoIUser.searchByUserName(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
                        erreursRef1.put(dataKey, "The user name already exist.");
                    }
                }
        ));
        user.setFirstName(validateData(request, FIRSTNAME, null));
        user.setLastName(validateData(request, LASTNAME, null));
        user.setEmail(validateData(request, EMAIL,
                (dataKey, erreursRef1) -> {
                    if (daoIUser.searchByEmail(FrameworkSupport.getTrimedValue(request, dataKey)) != null) {
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
            daoIUser.create(user);
        } else {
            setResult("Your account has not been created, please verify the entered information...");
        }

        return user;
    }

    private void deleteAccount(DAOIUser daIUser, DAOITweet daoITweet, DAOIFollow daoIFollow) {
        int userId = ((User) request.getSession().getAttribute(USER_SESSION)).getUserId();

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
        if (null != (user = (User) request.getSession().getAttribute(USER_SESSION))) {
            return user;
        }

        if ((userName = FrameworkSupport.getTrimedValue(request, USERNAME)).isEmpty()) {
            bEmptyFieldError = true;
            setErreur(USERNAME, "empty username");
        }
        if ((password = FrameworkSupport.getTrimedValue(request, PASSWORD)).isEmpty()) {
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
        Part part = imageValidation(request);
        User user = (User) request.getSession().getAttribute(USER_SESSION);

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

    private Part imageValidation(HttpServletRequest request) {
        try {
            return request.getPart(IMAGE);
        } catch (IllegalStateException | IOException | ServletException e) {
            setErreur(IMAGE, e.getMessage());
        }
        return null;

    }
}
