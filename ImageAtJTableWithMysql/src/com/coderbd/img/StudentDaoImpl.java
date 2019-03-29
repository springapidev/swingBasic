/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rajaul
 */
public class StudentDaoImpl implements StudentDao {

    static Connection conn = DbConnection.getConnection();

    public static void main(String[] args) {
        createTable();
    }

    public static void createTable() {
        //  String sql = "create table student(id int(11) auto_increment primary key, name varchar(50) not null, photo BLOB not null, file_path varchar(155), file_name varchar(155)";
        String sql = "CREATE TABLE `student` ( `id` INT NOT NULL auto_increment, `name` TEXT NOT NULL , `photo` BLOB NOT NULL , `file_path` varchar(150),`file_name` varchar(100) NOT NULL, PRIMARY KEY (`id`))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(Student student, File file) {
        FileInputStream inputStream = null;

        String sql = "insert into student(name, photo, file_path, file_name) values(?,?,?,?)";
        try {
            inputStream = new FileInputStream(file);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setBinaryStream(2, (InputStream) inputStream, (int) (file.length()));
            ps.setString(3, student.getFilePath());
            ps.setString(4, student.getFileName());

            ps.executeUpdate();
            System.out.println("Insert success");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> getList() {
        List<Student> list = new ArrayList();
        String sql = "select * from student";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getString(2), rs.getBytes("photo"), rs.getString(4), rs.getString(5));
                list.add(student);
            }
            System.out.println("Insert success");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
