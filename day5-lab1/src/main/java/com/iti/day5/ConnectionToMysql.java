package com.iti.day5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToMysql {
    private static ConnectionToMysql instance;
    private Connection connection;

    private ConnectionToMysql() {

        // write how to connect to db
        String databaseURL = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "iti";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(databaseURL, "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionToMysql getInstance() {
        if (instance == null) {
            instance = new ConnectionToMysql();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
