package com.parvez.conn;

import java.sql.Connection;

/**
 *
 * @author Parvez
 */
public class TestMySqlConnection {
    public static void main(String[] args) {
        Connection conn=MySqlDbConnection.getConnection();
    }
 
}
