package ua.hillel;

import java.sql.*;

public class DatabaseConnector {

    private final String url;
    private final String user;
    private final String password;

    public DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to connect to database" + e.getMessage());
        }
    }
}
