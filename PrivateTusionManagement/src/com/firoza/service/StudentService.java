package com.firoza.service;

import com.firoza.conn.MySqlDbConnection;
import com.firoza.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.firoza.dao.StudentDao;

/**
 *
 * @author J2EE-33
 */
public class StudentService implements StudentDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(Student s) {
        try {
            if (s.getStudentID() != 0) {

                PreparedStatement stmt = conn.prepareStatement("insert into student(student_id, name,fatherOrMother,mobile, className,monthlyFee,noOfMonth,totalFee,addmissionDate) values(?,?,?,?,?,?,?,?,?)");

                stmt.setInt(1, s.getStudentID());
                stmt.setString(2, s.getName());
                stmt.setString(3, s.getFatherOrMother());
                stmt.setString(4, s.getMobile());
                stmt.setString(5, s.getClassName());
                stmt.setDouble(6, s.getMonthlyFee());
                stmt.setInt(7, s.getNoOfMonth());
                stmt.setDouble(8, s.getTotalFee());
                stmt.setString(9, s.getAddmissionDate());

                int i = stmt.executeUpdate();
                System.out.println(i + " Student inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Student s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update student SET name=?,fatherOrMother=?,className=?,monthlyFee=?, noOfMonth=?, totalFee=?  where id=?");
                stmt.setString(1, s.getName());
                stmt.setString(2, s.getFatherOrMother());
                stmt.setString(3, s.getMobile());
                stmt.setString(4, s.getClassName());
                stmt.setDouble(5, s.getMonthlyFee());
                stmt.setInt(6, s.getNoOfMonth());
                stmt.setDouble(7, s.getTotalFee());
                stmt.setInt(8, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from student where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " record Deleted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getList() {
        List<Student> list = new ArrayList<>();
        try {
            Student p;
            PreparedStatement stmt = conn.prepareStatement("select * from student");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new Student();
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setFatherOrMother(rs.getString(4));
                p.setMobile(rs.getString(5));
                p.setClassName(rs.getString(6));
                p.setMonthlyFee(rs.getInt(7));
                p.setNoOfMonth(rs.getInt(8));
                p.setTotalFee(rs.getInt(9));
                p.setAddmissionDate(rs.getString(10));

                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Student getStudentByID(int id) {
        Student p = new Student();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from student where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setFatherOrMother(rs.getString(43));
                p.setMobile(rs.getString(5));
                p.setClassName(rs.getString(6));
                p.setMonthlyFee(rs.getInt(7));
                p.setNoOfMonth(rs.getInt(8));
                p.setTotalFee(rs.getInt(9));
                p.setAddmissionDate(rs.getString(10));

            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
