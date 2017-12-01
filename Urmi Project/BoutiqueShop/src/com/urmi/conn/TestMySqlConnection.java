package com.urmi.conn;

import java.sql.Connection;

/**
 *
 * @author Sunzida nasrin
 */
public class TestMySqlConnection {
    public static void main(String[] args) {
        Connection conn=MySqlDbConnection.getConnection();
    }
 
}
