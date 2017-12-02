/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.jdbc.insertdataintotable;

import com.coderbd.jdbc.domain.Student;
import com.coderbd.jdbc.connections.MySqlDbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajaul Islam
 */
public class TestInsert {

  static Connection conn = MySqlDbConnection.getConnection();

    public static void save(Student s) {

        try {
            if (s.getName() != null) {
                PreparedStatement stmt = conn.prepareStatement("insert into student(name,age ) values(?,?)");

                stmt.setString(1, s.getName());
                stmt.setInt(2, s.getAge());

                int i = stmt.executeUpdate();
                System.out.println(i + " records inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
