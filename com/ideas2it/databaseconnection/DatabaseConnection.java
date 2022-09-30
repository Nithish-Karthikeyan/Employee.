package com.ideas2it.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is Singleton class used for Database Connection
 * used for opening and closing the connection to the database 
 * 
 * @author Nithish K
 * @version 1.0
 * @since 19.09.2022
 */
public class DatabaseConnection {
    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/employee";
    private static final String USER = "root";
    private static final String PASSWORD = "Nithish@ideas2it";
    
    private DatabaseConnection() {}  
 
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }     

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}