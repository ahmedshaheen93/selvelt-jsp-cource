package com.shaheen.repository.adapter;

import com.shaheen.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ModelAdapter {


    private ModelAdapter() {
    }



    /**
     * map resultset to a user object without list of chat group  Or list of friends
     *
     * @param resultSet query resultset
     * @return user object
     */
    public static User mapResultSetToUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("ID"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setPhone(resultSet.getString("PHONE"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setCountry(resultSet.getString("COUNTRY"));
            user.setGender(Gender.values()[resultSet.getInt("GENDER")]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * map user fields to preparedStatement parameters as following
     * 1- FIRST_NAME ,
     * 2- LAST_NAME,
     * 3- PHONE,
     * 4- PASSWORD,
     * 5- EMAIL,
     * 6- COUNTRY,
     * 7- GENDER,
     * 8- DATE_OF_BIRTH,
     * 9- BIO,
     * 10- ONLINE,
     * 11- MODE
     *
     * @param preparedStatement that required to be map in
     * @param user              map its fields to preparedStatement parameters
     */
    public static void mapUsertoPreparedStatement(PreparedStatement preparedStatement, User user) {
        try {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getCountry());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
