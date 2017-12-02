/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.jdbc.del;

import com.coderbd.jdbc.connections.MySqlDbConnection;
import com.coderbd.jdbc.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajail Islam
 */
public class TestDel {
    
    static Connection conn = MySqlDbConnection.getConnection();

    public static void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from student where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " records Deleted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
