package com.ephec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ephec.beans.User;
import com.ephec.exceptions.DAOException;
import com.ephec.utility.UserUtility;

public class DAOFollow implements DAOIFollow {

    private DAOFactory daoFactory;


    public DAOFollow(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static final String SQL_INSERT = "INSERT INTO follow (followerId, followingId) VALUES (?,?)";
    private static final String SQL_DELETE = "DELETE FROM follow where (followerid = ? and followingid = ?)";
    private static final String SQL_DELTE_FOLLOWING = "DELETE FROM follow where followerid = ?";
    private static final String SQL_DELTE_FOLLOWER = "DELETE FROM follow where followingid = ?";


    @Override
    public void create(User user, String followingId) throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet AutoGeneratedValue = null;

        try {
            // Récupération d'une connexion depuis la Factory
            connexion = daoFactory.getConnection();
            preparedStatement = UserUtility.preparedRequestInitialization(
                    connexion, SQL_INSERT, true, user.getUserId(), followingId);
            int status = preparedStatement.executeUpdate();
            // Analyse du statut retourné par la requête d'insertion
            if (status == 0) {
                throw new DAOException(
                        "Échec de la création du follow l'utilisateur, aucune ligne ajoutée dans la table.");
            }
            // Récupération de l'id auto-généré par la requête d'insertion
            AutoGeneratedValue = preparedStatement.getGeneratedKeys();
            if (AutoGeneratedValue.next()) {

            } else {
                throw new DAOException(
                        "Échec de la création du follow en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOClose.silentClose(AutoGeneratedValue,
                    preparedStatement, connexion);
        }

    }

    @Override
    public void delete(User user, String followingId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet AutoGeneratedValue = null;

        try {
            // Récupération d'une connexion depuis la Factory
            connexion = daoFactory.getConnection();
            preparedStatement = UserUtility.preparedRequestInitialization(
                    connexion, SQL_DELETE, false, user.getUserId(), followingId);
            int status = preparedStatement.executeUpdate();
            // Analyse du statut retourné par la requête d'insertion
            if (status == 0) {
                throw new DAOException(
                        "Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOClose.silentClose(AutoGeneratedValue,
                    preparedStatement, connexion);
        }

    }

    @Override
    public void deleteUserFollowing(int userId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet AutoGeneratedValue = null;

        try {
            // Récupération d'une connexion depuis la Factory
            connexion = daoFactory.getConnection();
            preparedStatement = UserUtility.preparedRequestInitialization(
                    connexion, SQL_DELTE_FOLLOWING, false, userId);
            int status = preparedStatement.executeUpdate();
            // Analyse du statut retourné par la requête d'insertion
            if (status == 0) {
                throw new DAOException(
                        "Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOClose.silentClose(AutoGeneratedValue,
                    preparedStatement, connexion);
        }

    }

    @Override
    public void deleteUserFollower(int userId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet AutoGeneratedValue = null;

        try {
            // Récupération d'une connexion depuis la Factory
            connexion = daoFactory.getConnection();
            preparedStatement = UserUtility.preparedRequestInitialization(
                    connexion, SQL_DELTE_FOLLOWER, false, userId);
            int status = preparedStatement.executeUpdate();
            // Analyse du statut retourné par la requête d'insertion
            if (status == 0) {
                throw new DAOException(
                        "Échec de la suppression du follow l'utilisateur, aucune ligne n'a été supprimé de la table.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOClose.silentClose(AutoGeneratedValue,
                    preparedStatement, connexion);
        }

    }

}
