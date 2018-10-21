package com.coderbd.daoImpl;

import com.coderbd.connection.DbConnection;
import com.coderbd.dao.StudentDao;
import com.coderbd.domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDaoImpl implements StudentDao {
    
    Connection conn = DbConnection.getConnection("xe", "hr", "hr");
//"create table student(id number(5) primary key,name varchar2(55))"

    @Override
    public void createTable(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("1 Table has been created");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void insert(Student s) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into student(id, name) values(?,?)");
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.executeUpdate();
            System.out.println("1 Data has been Inserted");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(Student s) {
        try {
            PreparedStatement ps = conn.prepareStatement("update student set name=? where id=?");
            ps.setString(1, s.getName());
            ps.setInt(2, s.getId());
            ps.executeUpdate();
            System.out.println("1 Data has been uipdated");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(Student s) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from student where id=?");
            ps.setInt(1, s.getId());
            ps.executeUpdate();
            System.out.println("1 Data has been Deleted");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Student getById(int id) {
        Student s = new Student();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    @Override
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
