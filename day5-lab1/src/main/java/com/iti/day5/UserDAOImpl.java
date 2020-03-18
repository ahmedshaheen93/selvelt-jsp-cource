package com.iti.day5;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private final String select_By_username_and_password = "SELECT * FROM USER WHERE USERNAME= ? AND PASSWORD = ?";
    private final String insert = "INSERT INTO USER (FIRSTNAME ,LASTNAME,USERNAME,PASSWORD) VALUES" +
            " (?,?,?,?)";

    // connection
    private Connection connection = null;
    // preparedStatment
    private PreparedStatement preparedStatement = null;
    // resultSet
    private ResultSet resultSet = null;

    public UserDAOImpl() {
        connection = ConnectionToMysql.getInstance().getConnection();
    }


    @Override
    public User login(String username, String password) {

        try {
            preparedStatement = connection.prepareStatement(select_By_username_and_password);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setUsername(resultSet.getString("username"));
                //optional return password
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User register(User user) {
        try {
            // id user =>
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            //
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }//finally colse pr rs
        return user;
    }
}
