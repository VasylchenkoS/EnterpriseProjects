package com.vasylchenko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class Runner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "user";
    private String password = "123";

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver");
            throw new RuntimeException(e);
        }
    }

    public void getAll() {
        LOGGER.info("Connecting to DB");
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            LOGGER.info("Successfully connected to DB");
//            String sql = "SELECT * FROM *";
//            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB. URL: " + url);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        loadDriver();
        new Runner().getAll();
    }
}
