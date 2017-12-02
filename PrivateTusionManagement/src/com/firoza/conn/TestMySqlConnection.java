package com.firoza.conn;

import java.sql.Connection;

/**
 *
 * @author Firoza Akter
 */
public class TestMySqlConnection {
    public static void main(String[] args) {
        Connection conn=MySqlDbConnection.getConnection();
    }
 
}
