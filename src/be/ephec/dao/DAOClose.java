package be.ephec.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOClose {

    public static void silentClose(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }

    public static void silentClose(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }

    public static void silentClose(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    public static void silentClose(Statement statement, Connection connexion) {
        silentClose(statement);
        silentClose(connexion);
    }

    public static void silentClose(ResultSet resultSet, Statement statement, Connection connexion) {
        silentClose(resultSet);
        silentClose(statement);
        silentClose(connexion);
    }
}
