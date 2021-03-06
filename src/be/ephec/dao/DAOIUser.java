package be.ephec.dao;

import be.ephec.beans.Dashboard;
import be.ephec.beans.User;
import be.ephec.exceptions.DAOException;

import java.util.List;

public interface DAOIUser {

    void create(User user) throws DAOException;

    void update(User user) throws DAOException;

    User searchByUserName(String userName) throws DAOException;

    User searchByEmail(String email) throws DAOException;

    User loginSearch(String pUserName, String pPassword);

    User searchById(int userId);

    List<User> searchFollowingByAnyNameLike(String keyword, int userId) throws DAOException;

    List<User> searchNotFollowingByAnyNameLike(String keyword, int userId) throws DAOException;

    void delete(int userId) throws DAOException;

    public Dashboard getDashboard(User user);

    User searchById(int userId, User currentUser);
}
