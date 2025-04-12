package com.abc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/postify1";
    private static final String USER = "root";
    private static final String PASSWORD = "041013";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load driver
            Class.forName(DRIVER_CLASS);
            System.out.println("Driver loaded successfully: " + DRIVER_CLASS);

            // Attempt to establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối Database thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver: " + DRIVER_CLASS);
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối Database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}