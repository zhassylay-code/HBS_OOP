package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/hotel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0001";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
