package be.ephec.dao;

import be.ephec.beans.ReTweet;
import be.ephec.beans.TweetIn;
import be.ephec.beans.TweetOut;
import be.ephec.beans.User;
import be.ephec.exceptions.DAOException;
import be.ephec.utilities.EntityMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOTweet extends DAO implements DAOITweet {

    private static final String SQL_INSERTTWEETIN = "INSERT INTO tweet (UserId, Body, UpdatedAt) VALUES (?,?, NOW())";
    private static final String SQL_SELECTTWEETOUT = "select  sel.tid as tweetId, sel.tm as body, sel. ruid as ruid, sel.par, " +
            "sel.uorig, u.userName as userName, u.image as uorigImage, sel.updatedAt " +
            " from ( SELECT  t.tweetId as tid, t.body as tm, r.UserId as ruid, u.username as par, t.UserId as uorig, r.UpdatedAt as updatedAt " +
            " FROM tweet as t " +
            " right join retweet as r " +
            " on r.TweetId = t.TweetId and r.UserId in (select f.followingid from follow as f  where f.followerid = ?) " +
            " left join user as u " +
            " on r.UserId = u.UserId " +
            " WHERE ((t.userid) in (select f.followingid from follow as f  where f.followerid = ?)) " +
            " union " +
            " SELECT  t.tweetId as tid, t.body  as tm, -1, null,t.UserId as uorig, t.UpdatedAt as updatedAt " +
            " FROM tweet as t " +
            " WHERE ((t.userid) in (select f.followingid from follow as f  where f.followerid = ?)) and (t.TweetId) not in (select r.TweetId from retweet as r  where r.TweetId=t.TweetId) " +
            " union " +
            " SELECT  t.tweetId as tid, t.body  as tm, 0, null,t.UserId as uorig, t.UpdatedAt as updatedAt " +
            " FROM tweet as t " +
            " WHERE ((t.userid) in (select f.followingid from follow as f  where f.followerid = ?)) and (t.TweetId) in (select r.TweetId from retweet as r  where r.TweetId=t.TweetId)) " +
            " as sel " +
            " left join user as u " +
            " on sel.uorig = u.UserId " +
            " order by updatedAt desc " +
            " limit 25";
    private static final String SQL_INSERTRETWEET = "INSERT INTO retweet(TweetId, UserId, UpdatedAt) VALUES (?,?,NOW())";
    private final String SQL_DELETE_RETWEET = "DELETE FROM retweet where userid = ?";
    private final String SQL_DELETE_TWEET = "DELETE FROM tweet where userid = ?";

    DAOTweet(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public TweetIn createTweet(TweetIn tweet) {
        try {
            this.executeUpdate(SQL_INSERTTWEETIN, (status, AutoGeneratedValue) -> {
                if (status == 0) {
                    throw new DAOException(
                            "Échec de la création du Tweet, aucune ligne ajoutée dans la table.");
                }
                if (AutoGeneratedValue.next()) {
                    tweet.setTwitId(AutoGeneratedValue.getInt(1));
                } else {
                    throw new DAOException("Échec de la création du Tweet en base, aucun ID auto-généré retourné.");
                }
                this.CloseConnection();
            }, tweet.getUserId(), tweet.getBody());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return tweet;
    }

    @Override
    public void deleteTweet(int userId) {
        try {
            this.executeUpdate(SQL_DELETE_TWEET, (status, resultSet) -> {
                if (status == 0) {
                    throw new DAOException("Échec de la création du Tweet, aucune ligne ajoutée dans la table.");
                }
            }, userId);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteReTweet(int userId) {
        try {
            this.executeUpdate(SQL_DELETE_RETWEET, (status, resultSet) -> {
                if (status == 0) {
                    throw new DAOException(
                            "Échec de la création du Tweet, aucune ligne ajoutée dans la table.");
                }
            }, userId);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<TweetOut> getTweetOutList(User user) {
        List<TweetOut> tweetsOut = new ArrayList<TweetOut>();
        try {
            ResultSet resultSet = this.executeQuery(SQL_SELECTTWEETOUT, false, user.getUserId(), user.getUserId(), user.getUserId(), user.getUserId());
            while (resultSet.next()) {
                EntityMapping<TweetOut> EntityMapping = new EntityMapping<>(TweetOut.class);
                tweetsOut.add(EntityMapping.getMapping(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseConnection();
        }
        return tweetsOut;
    }

    @Override
    public ReTweet reTweet(ReTweet reTweet) {
        try {
            this.executeUpdate(SQL_INSERTRETWEET, (status, AutoGeneratedValue) -> {
                if (status == 0) {
                    throw new DAOException(
                            "Échec de la création du Tweet, aucune ligne ajoutée dans la table.");
                }
                if (AutoGeneratedValue.next()) {
                    reTweet.setTweetId(AutoGeneratedValue.getInt(1));
                } else {
                    throw new DAOException("Échec de la création du Tweet en base, aucun ID auto-généré retourné.");
                }
                this.CloseConnection();
            }, reTweet.getReTweetid(), reTweet.getUserId());
        } catch (SQLException e) {
            this.CloseConnection();
            throw new DAOException(e);
        }
        return reTweet;
    }
}
