package com.shaheen.config.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToMysql {
    private Connection connection;

    public ConnectToMysql(String databaseIP ,int databasePortNumber ,String databaseName
        ,String userName ,String userPassword) {
        String databaseURL = "jdbc:mysql://" + databaseIP + ":" + databasePortNumber + "/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(databaseURL, userName, userPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {

        return connection;
    }

}
