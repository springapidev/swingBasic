package com.coderbd.jdbc.connections;

import java.sql.Connection;

/**
 *
 * @author Rajail Islam
 */
public class TestMySqlConnection {
    public static void main(String[] args) {
        Connection conn=MySqlDbConnection.getConnection();
    }
 
}
