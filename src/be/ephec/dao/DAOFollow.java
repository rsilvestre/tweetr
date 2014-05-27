package be.ephec.dao;

import be.ephec.beans.User;
import be.ephec.exceptions.DAOException;
import be.ephec.utilities.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOFollow extends DAO implements DAOIFollow {

    private static final String SQL_INSERT = "INSERT INTO follow (followerId, followingId) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM follow where (followerid = ? and followingid = ?)";
    private static final String SQL_DELTE_FOLLOWING = "DELETE FROM follow where followerid = ?";
    private static final String SQL_DELTE_FOLLOWER = "DELETE FROM follow where followingid = ?";
    private static final String SELECT_FOLLOWING_USER_BY_FOLLOWER = "select result1.userId, result1.userName, result1.firstName, result1.lastName, result1.email, result1.password, result1.image, result1.updatedAt, if(result2.follower,1,0) as follower from (select u.*, 0 as follower from User as u left join Follow as f on u.UserId = f.FollowingId where f.FollowerId = ?) as result1 left join (select u.userId, 1 as follower from User as u left join Follow as f on u.UserId = f.FollowingId where f.FollowerId = ?) as result2 on result1.userId = result2.userId;";
    private static final String SELECT_FOLLOWER_USER_BY_FOLLOWING = "select result1.userId, result1.userName, result1.firstName, result1.lastName, result1.email, result1.password, result1.image, result1.updatedAt, if(result2.follower, 1, 0) as follower from (select * from (select result.userId, result.userName, result.firstName, result.lastName, result.email, result.password, result.image, result.updatedAt, if(sum(result.follower) = 1 and sum(result.following) = 1, 1, 0) as follower from (select u.*, 1 as follower, 0 as following from User as u left join Follow as f on u.UserId = f.FollowerId where f.FollowingId = ? union select u.*, 0 as follower, 1 as following from User as u left join Follow as f on u.UserId = f.FollowingId where f.FollowerId = ?) as result group by result.Email) as final where final.userId in (select u.userId from User as u left join Follow as f on u.UserId = f.FollowerId where f.FollowingId = ?) ) as result1 left join (select u.userId, 1 as follower from User as u left join Follow as f ON u.UserId = f.FollowingId where f.FollowerId = ? ) as result2 on result1.userID = result2.userID;";

    public DAOFollow(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(User user, String followingId) throws DAOException {
        try {
            int status = this.executeUpdate(SQL_INSERT, user.getUserId(), followingId);
            if (status == 0) {
                throw new DAOException(
                        "Échec de la création du follow l'utilisateur, aucune ligne ajoutée dans la table.");
            }
            if (!this.getResultSet().next()) {
                throw new DAOException(
                        "Échec de la création du follow en base, aucun ID auto-généré retourné.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
    }

    @Override
    public void delete(User user, String followingId) throws DAOException {
        try {
            int status = this.executeUpdate(SQL_DELETE, user.getUserId(), followingId);

            if (status == 0) {
                throw new DAOException("Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
    }

    @Override
    public void deleteUserFollowing(int userId) throws DAOException {
        try {
            int status = this.executeUpdate(SQL_DELTE_FOLLOWING, userId);
            if (status < 0) {
                throw new DAOException(
                        "Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
    }

    @Override
    public void deleteUserFollower(int userId) throws DAOException {
        try {
            int status = this.executeUpdate(SQL_DELTE_FOLLOWER, userId);
            if (status < 0) {
                throw new DAOException(
                        "Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.CloseConnection();
        }
    }

    @Override
    public List<User> getFollowerByUser(User user2, User user1) throws DAOException {
        List<User> lstUser = new ArrayList<>();
        try {
            ResultSet resultSet = this.executeQuery(SELECT_FOLLOWER_USER_BY_FOLLOWING, false, user2.getUserId(), user2.getUserId(), user2.getUserId(), user1.getUserId());
            while (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                try {
                    lstUser.add(EntityMapping.getMapping(resultSet));
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.CloseConnection();
        }
        return lstUser;
    }

    @Override
    public List<User> getFollowingByUser(User user2, User user1) throws DAOException {
        List<User> lstUser = new ArrayList<>();
        try {
            ResultSet resultSet = this.executeQuery(SELECT_FOLLOWING_USER_BY_FOLLOWER, false, user2.getUserId(), user1.getUserId());
            while (resultSet.next()) {
                EntityMapping<User> EntityMapping = new EntityMapping<>(User.class);
                try {
                    lstUser.add(EntityMapping.getMapping(resultSet));
                } catch (Exception e) {
                    throw new DAOException(e);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            this.CloseConnection();
        }
        return lstUser;
    }

    @Override
    public List<User> getFollowingByUser(User user) {
        return this.getFollowingByUser(user, user);
    }

    @Override
    public List<User> getFollowerByUser(User user) {
        return this.getFollowerByUser(user, user);
    }
}
