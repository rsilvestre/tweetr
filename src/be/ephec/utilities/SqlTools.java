package be.ephec.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by michaelsilvestre on 17/05/14.
 */
public class SqlTools {

    public static PreparedStatement preparedRequestInitialization(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets) throws SQLException {
        Integer generatedKey = returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
        PreparedStatement preparedStatement = connexion.prepareStatement(sql, generatedKey);

        for (int i = 0, end = objets.length; i < end; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }

        System.out.println("preparedStatement.toString() " + preparedStatement.toString());

        return preparedStatement;
    }
}
