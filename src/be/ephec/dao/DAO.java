package be.ephec.dao;

import be.ephec.utilities.SqlTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by michaelsilvestre on 19/05/14.
 */
public abstract class DAO {
    private final DAOFactory daoFactory;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    protected DAOFactory getDaoFactory() {
        return this.daoFactory;
    }

    public void executeUpdate(String SQL, updateCallback next, Object... objects) throws SQLException {
        int status = executePreparedStatement(SQL, true, objects).executeUpdate();
        resultSet = this.preparedStatement.getGeneratedKeys();
        next.doJob(status, resultSet);
    }

    public int executeUpdate(String SQL, Object... objects) throws SQLException {
        int status = executePreparedStatement(SQL, true, objects).executeUpdate();
        resultSet = this.preparedStatement.getGeneratedKeys();
        return status;
    }

    public void executeQuery(String SQL, Boolean returnGeneratedKeys, queryCallback next, Object... objects) throws SQLException {
        resultSet = executePreparedStatement(SQL, returnGeneratedKeys, objects).executeQuery();
        next.doJob(resultSet);
    }

    public ResultSet executeQuery(String SQL, Boolean returnGeneratedKeys, Object... objects) throws SQLException {
        resultSet = executePreparedStatement(SQL, returnGeneratedKeys, objects).executeQuery();
        return resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        if (preparedStatement == null) {
            return null;
        }
        return this.preparedStatement;
    }

    protected void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    private PreparedStatement executePreparedStatement(String SQL, Boolean returnGeneratedKeys, Object... objects) throws SQLException {
        this.preparedStatement = SqlTools.preparedRequestInitialization(getConnection(), SQL, returnGeneratedKeys, objects);
        return this.preparedStatement;
    }

    protected ResultSet getResultSet() {
        return this.resultSet;
    }

    protected Connection getConnection() throws SQLException {
        if (connection == null) {
            this.connection = daoFactory.getConnection();
        }
        return connection;
    }

    protected void CloseConnection() {
        DAOClose.silentClose(resultSet, preparedStatement, connection);
        connection = null;
        resultSet = null;
        preparedStatement = null;
    }

    public interface updateCallback {
        void doJob(int Status, ResultSet resultSet) throws SQLException;
    }

    public interface queryCallback {
        void doJob(ResultSet resultSet) throws SQLException;
    }
}
