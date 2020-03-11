package com.shaheen.repository.impl;

import com.shaheen.repository.*;
import com.shaheen.repository.adapter.*;
import com.shaheen.model.*;
import com.shaheen.config.database.connection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String SELECT_ALL = "SELECT * FROM USER";
    private final String SEARCH_BY_NAME = "SELECT * FROM USER "
    +"WHERE FIRST_NAME LIKE '%' ? '%' OR LAST_NAME LIKE '%' ? '%' ";
    private final String SELECT_BY_ID = "SELECT * FROM USER WHERE ID = ?";
    private final String SELECT_BY_PHONE_PASSWORD = "SELECT * FROM USER WHERE PHONE = ? " +
            "AND PASSWORD = ?";
    private final String INSERT_USER 
    = "INSERT INTO USER (FIRST_NAME,LAST_NAME,PHONE,PASSWORD,EMAIL," +
            "COUNTRY)" +
            "  VALUES (?,?,?,?,?,?)";
    private final String UPDATE_USER = "UPDATE USER SET FIRST_NAME= ?," +
            "LAST_NAME = ?,PHONE = ?,PASSWORD = ? ,EMAIL = ?," +
            " COUNTRY =? ,GENDER = ?,DATE_OF_BIRTH =?,BIO = ?,ONLINE = ?,MODE = ?,IMAGE=?" +
            " WHERE ID = ?";
    private final String DELETE_USER = "DELETE FROM USER WHERE ID = ?";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public UserRepositoryImpl(ConnectToMysql connectToMysql) {

        connection = connectToMysql.getConnection();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = ModelAdapter.mapResultSetToUser(resultSet);
                user.setPassword("");
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSetAndPreparedStatement(resultSet, preparedStatement);
        }
        return users;
    }
    @Override
    public List<User> excuteQuery(String query) throws SQLException{
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = ModelAdapter.mapResultSetToUser(resultSet);
                user.setPassword("");
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw e;
        } finally {
            closeResultSetAndPreparedStatement(resultSet, preparedStatement);
        }
        
    }

    @Override
    public List<User> search(String name) {
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = ModelAdapter.mapResultSetToUser(resultSet);
                user.setPassword("");
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSetAndPreparedStatement(resultSet, preparedStatement);
        }
        return users;
    }

    @Override
    public User findById(int id, boolean fullData) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (fullData) {
                    int userId = resultSet.getInt("ID");
                    user = ModelAdapter.mapResultSetToUser(resultSet);
                } else {
                    user = ModelAdapter.mapResultSetToUser(resultSet);
                }
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSetAndPreparedStatement(resultSet, preparedStatement);
        }
        return user;

    }

    @Override
    public User findByPhoneAndPassword(String phone, String password) {

        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

            try {
                preparedStatement = connection.prepareStatement(SELECT_BY_PHONE_PASSWORD);
                preparedStatement.setString(1, phone);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int userId = resultSet.getInt("ID");
                    user = ModelAdapter.mapResultSetToUser(resultSet);
                }
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResultSetAndPreparedStatement(resultSet, preparedStatement);
            }
        
        return user;
    }

    @Override
    public User insertUser(User user, String password) {

        int id = -1;
        try {
            user.setPassword(password);
            preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ModelAdapter.mapUsertoPreparedStatement(preparedStatement, user);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            user.setId(id);
            user.setPassword("");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User updateUser(User user, String password) {

        User updated = null;
        user.setPassword(password);
        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            ModelAdapter.mapUsertoPreparedStatement(preparedStatement, user);
            preparedStatement.setLong(13, user.getId());
            int res = preparedStatement.executeUpdate();
            if (res > 0)
                updated = user;
            user.setPassword("");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    @Override
    public int delete(int id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
      @Override
    public int excuteUpdate(String query) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
           throw e;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
    }


    private void closeResultSetAndPreparedStatement(ResultSet resultSet, PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}