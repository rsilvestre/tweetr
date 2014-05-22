package be.ephec.dao;

import be.ephec.beans.User;
import be.ephec.exceptions.DAOException;

import java.util.List;

public interface DAOIFollow {

    void create(User user, String followingId) throws DAOException;

    void delete(User user, String followingId) throws DAOException;

    void deleteUserFollowing(int userId) throws DAOException;

    void deleteUserFollower(int userId) throws DAOException;

    List<User> getFollowerByUser(User user1, User user2);

    List<User> getFollowingByUser(User user1, User user2);

    List<User> getFollowingByUser(User user);

    List<User> getFollowerByUser(User user);
}
