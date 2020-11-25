package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String URL = "jdbc:postgresql://localhost:5432/semester-work-db";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "zxcdfg270301";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }

}
