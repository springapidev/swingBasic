package com.ss.bms.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class MySqlDbConnection {
    private static final String HOST = "jdbc:mysql://localhost:3306";
    private static final String DBNMAE = "student";
    private static final String URL = HOST + "/" + DBNMAE ;
    private static Connection con = null;

    public static Connection getConnection(String dbUserName, String dbPass) {
        try {
            con = DriverManager.getConnection(URL, dbUserName, dbPass);
            System.out.println("::Connected::");
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
