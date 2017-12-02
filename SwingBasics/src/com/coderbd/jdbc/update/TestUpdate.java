package com.coderbd.jdbc.update;

import com.coderbd.jdbc.connections.MySqlDbConnection;
import com.coderbd.jdbc.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajaul Islam
 */
public class TestUpdate {

    static Connection conn = MySqlDbConnection.getConnection();

    public static void update(Student s) {

        try {
            if (s.getName() != null) {
                PreparedStatement stmt = conn.prepareStatement("update student set name=?, age=? where id=?");

                stmt.setString(1, s.getName());
                stmt.setInt(2, s.getAge());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();
                System.out.println(i + " records Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
