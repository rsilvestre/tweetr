package com.ephec.dao;

import java.util.List;

import com.ephec.beans.User;
import com.ephec.exceptions.DAOException;

public interface DAOIUser {

    void create(User user) throws DAOException;

    void update(User user) throws DAOException;

    User searchByUserName(String userName) throws DAOException;

    User searchByEmail(String email) throws DAOException;

    List<User> searchFollowingByAnyNameLike(String keyword, int userId) throws DAOException;

    List<User> searchNotFollowingByAnyNameLike(String keyword, int userId) throws DAOException;

    void delete(int userId) throws DAOException;
}
