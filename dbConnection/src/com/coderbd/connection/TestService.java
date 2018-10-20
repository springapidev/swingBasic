package com.coderbd.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestService {

    static Connection conn = DbConnection.getConnection("xe", "hr", "hr");

    public static void getEmployeeList() {
        // String sql="select * from employees";
        try {
            PreparedStatement ps = conn.prepareStatement("select * from emp");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("" + rs.getString(1) + " " + rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateEmplyee() {
        try {
            PreparedStatement ps = conn.prepareStatement("update employees set first_name='Bangladesh' where employee_id=100");
            int i = ps.executeUpdate();
            System.out.println(i + " data has been updated");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void delEmplyee() {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from employees where employee_id=206");
            int i = ps.executeUpdate();
            System.out.println(i + " data has been deleted");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cteateEmplyeeTable() {
        try {
            PreparedStatement ps = conn.prepareStatement("create table emp(id number(3), name varchar(55))");
            int i = ps.executeUpdate();
            System.out.println(i + " table  has been created");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertEmplyee() {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into emp(id, name) values(206, 'William')");
            int i = ps.executeUpdate();
            System.out.println(i + " data has been Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(TestService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
