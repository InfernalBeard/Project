package hw2.util;

import hw2.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnectionManager {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Constants.DATABASE_URL,
                Constants.DATABASE_USER,
                Constants.DATABASE_PASSWORD
        );
    }
}