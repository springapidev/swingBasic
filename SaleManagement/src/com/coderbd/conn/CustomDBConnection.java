package com.coderbd.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomDBConnection {

    static Connection conn = null;

    public static Connection getDBConnection(String host, String port, String dbname, String username, String password) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname, username, password);
            System.out.println("Connected");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(CustomDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Connection getDBConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salesmgmt", "root", "1234");
            System.out.println("Connected");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(CustomDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
