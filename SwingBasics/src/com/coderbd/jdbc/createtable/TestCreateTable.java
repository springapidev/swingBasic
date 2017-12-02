/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.jdbc.createtable;

import com.coderbd.jdbc.connections.MySqlDbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class TestCreateTable {
    static Connection conn = MySqlDbConnection.getConnection();
    
    public static void main(String[] args) {
        String sql = "CREATE TABLE student " +
                   "(id INTEGER not NULL, " +
                   " name VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
        try {
            PreparedStatement pstm=conn.prepareStatement(sql);
            int i=pstm.executeUpdate();
            System.out.println(i+ "table has been created successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(TestCreateTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
