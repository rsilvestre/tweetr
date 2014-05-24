package be.ephec.dao;

import be.ephec.beans.Dashboard;
import be.ephec.beans.User;
import be.ephec.exceptions.DAOException;
import be.ephec.utilities.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUser extends DAO implements DAOIUser {

    private static final String SQL_INSERT = "INSERT INTO user (userName, firstName, lastName, email, password, image, updatedAt) VALUES (?,?,?, ?, ?,?, NOW())";
    private static final String SQL_LOGIN_USER = "SELECT * FROM User WHERE UserName = ? AND Password = ?";
    private static final String SQL_SEARCH_USER_BY_USERNAME = "SELECT * FROM User WHERE UserName = ?";
    private static final String SQL_SEARCH_USER_BY_EMAIL = "SELECT * FROM User WHERE Email = ?";
    private static final String SQL_SEARCH_USER_BY_ID = "SELECT * FROM User WHERE UserId = ?";
    private static final String SQL_SEARCH_USER_BY_ID_AND_CURRENT_USER = "select result.userId, result.userName, result.firstName, result.lastName, result.email, result.password, result.image, result.updatedAt, if (sum(result.follower)>0,1,0) as follower from (select *, 0 as follower from user as u where u.userId = ? union select u.*, 1 as follower from user as u left join Follow f on u.userID = f.FollowingId where u.userid = ? and f.FollowerId = ?) as result group by userId;";
    private static final String SQL_SEARCH_FOLLOWING_USER_BY_ANYNAME_LIKE = "SELECT distinct u.userId, u.userName, u.firstName, u.lastName, u.image, 1 as follower " +
            "FROM user as u " +
            "where (LOWER(u.userName like ?) or LOWER(u.firstName like ?) " +
            "or LOWER(u.lastName like ?)) " +
            "and (u.userid in (SELECT f.followingid from follow as f where f.followerid = ?)) and (u.userid <> ?)";
    private static final String SQL_SEARCH_NOT_FOLLOWING_USER_BY_ANYNAME_LIKE = "SELECT distinct u.userId, u.userName, u.firstName, u.lastName, u.image, 0 as follower " +
            "FROM user as u " +
            "where (LOWER(u.userName like ?) or LOWER(u.firstName like ?) " +
            "or LOWER(u.lastName like ?)) " +
            "and (u.userid not in (SELECT f.followingid from follow as f where f.followerid = ?)) and (u.userid <> ? )";
    private static final String SQL_UPDATE_USER = "UPDATE user set userName = ?, firstName = ?, lastName = ?, email = ?, password = ?, image = ? where userId = ? ";
    private final static String SQL_DELETE_USER = "DELETE FROM user where userid = ?";
    private final static String DASHBOARD_USER = "select sum(Dashboard.tweetNum) as tweetNumber, sum(Dashboard.followerNum) as followerNumber, sum(Dashboard.followingNum) as followingNumber from " +
            "(select 1 as id, count(*) as followingNum, 0 as followerNum, 0 as tweetNum from Follow where FollowerId = ? " +
            "union " +
            "select 1 as id, 0 as followerNum, count(*) as followerNumb, 0 as tweetNum from Follow where FollowingId = ? " +
            "union " +
            "select 1 as id, 0 as followerNum, 0 as followerNum, count(*) as tweetNum from Tweet where UserId = ?) as Dashboard " +
            "group by id";

    DAOUser(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(User user) throws DAOException {

        try {
            executeUpdate(SQL_INSERT, (status, result) -> {
                        if (status == 0) {
                            throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
                        }

                        if (result.next()) {
                            user.setUserId(result.getInt(1));
                        } else {
                            throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
                        }
                        this.CloseConnection();
                    },
                    user.getUserName(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getImage()
            );

        } catch (SQLException e) {
            this.CloseConnection();
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int userId) throws DAOException {

        try {
            this.executeUpdate(SQL_DELETE_USER, (status, result) -> {
                if (status < 0) {
                    throw new DAOException("Échec de la suppression de l'utilisateur, aucune ligne supprimée dans la table.");
                }
                this.CloseConnection();
            }, userId);
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void update(User user) throws DAOException {

        try {
            this.executeUpdate(SQL_UPDATE_USER, (status, result) -> {
                        if (status == 0) {
                            throw new DAOException("Échec de la modification de l'utilisateur, aucune ligne n'a été modifiée dans la table.");
                        }
                        this.CloseConnection();
                    },
                    user.getUserName(),
                    user.getFirstName(), user.getLastName(), user.getEmail(),
                    user.getPassword(), user.getImage(), user.getUserId()
            );
        } catch (SQLException e) {
            this.CloseConnection();
            throw new DAOException(e);
        }

    }

    @Override
    public User searchByUserName(String userName) throws DAOException {
        User user = null;
        try {
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_USER_BY_USERNAME, false, userName);
            if (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                user = EntityMapping.getMapping(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseConnection();
        }
        return user;
    }

    @Override
    public User loginSearch(String pUserName, String pPassword) throws DAOException {
        User user = null;
        try {
            ResultSet resultSet = this.executeQuery(SQL_LOGIN_USER, false, pUserName, pPassword);
            if (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                user = EntityMapping.getMapping(resultSet);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
        return user;
    }

    @Override
    public User searchById(int userId) throws DAOException {
        User user = null;

        try {
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_USER_BY_ID, false, userId);
            if (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                user = EntityMapping.getMapping(resultSet);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }

        return user;
    }

    @Override
    public User searchById(int userId, User currentUser) throws DAOException {
        User user = null;

        try {
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_USER_BY_ID_AND_CURRENT_USER, false, userId, userId, currentUser.getUserId());
            if (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                user = EntityMapping.getMapping(resultSet);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }

        return user;
    }

    @Override
    public User searchByEmail(String email) throws DAOException {
        User user = null;

        try {
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_USER_BY_EMAIL, false, email);
            if (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                user = EntityMapping.getMapping(resultSet);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }

        return user;
    }

    @Override
    public List<User> searchFollowingByAnyNameLike(String keyword, int userId) throws DAOException {
        List<User> userFollowingList = new ArrayList<>();

        try {
            String sqlKeyword = "%" + keyword + "%";
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_FOLLOWING_USER_BY_ANYNAME_LIKE, false, sqlKeyword, sqlKeyword, sqlKeyword, userId, userId);
            while (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                userFollowingList.add(EntityMapping.getMapping(resultSet));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
        return userFollowingList;
    }

    @Override
    public List<User> searchNotFollowingByAnyNameLike(String keyword, int userId) throws DAOException {
        List<User> userNotFollowingList = new ArrayList<>();

        try {
            String sqlKeyword = "%" + keyword + "%";
            ResultSet resultSet = this.executeQuery(SQL_SEARCH_NOT_FOLLOWING_USER_BY_ANYNAME_LIKE, false, sqlKeyword, sqlKeyword, sqlKeyword, userId, userId);
            while (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                userNotFollowingList.add(EntityMapping.getMapping(resultSet));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }

        return userNotFollowingList;
    }

    public Dashboard getDashboard(User user) throws DAOException {
        Dashboard dashboard = null;

        try {
            ResultSet resultSet = this.executeQuery(DASHBOARD_USER, false, user.getUserId(), user.getUserId(), user.getUserId());
            if (resultSet.next()) {
                EntityMapping<Dashboard> EntityMapping = new EntityMapping<>(Dashboard.class);
                dashboard = EntityMapping.getMapping(resultSet);
                dashboard.setUser(user);
            }
        } catch (Exception ex) {
            throw new DAOException(ex);
        } finally {
            this.CloseConnection();
        }
        return dashboard;
    }
}
