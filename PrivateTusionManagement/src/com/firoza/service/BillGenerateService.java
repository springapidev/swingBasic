/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.service;

import com.firoza.conn.MySqlDbConnection;
import com.firoza.dao.GenerateFeeDao;
import com.firoza.domain.BillGenerate;
import com.firoza.domain.Student;
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
 * @author Rajail Islam
 */
public class BillGenerateService implements GenerateFeeDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(BillGenerate s) {
        try {
            if (s.getStudentID() != 0) {

                PreparedStatement stmt = conn.prepareStatement("insert into bill(studentID, name,fatherOrMother,mobile, className,monthlyFee,noOfMonth,monthName,genDate) values(?,?,?,?,?,?,?,?,?)");
                stmt.setInt(1, s.getStudentID());
                stmt.setString(2, s.getName());
                stmt.setString(3, s.getFatherOrMother());
                stmt.setString(4, s.getMobile());
                stmt.setString(5, s.getClassName());
                stmt.setDouble(6, s.getMonthlyFee());
                stmt.setInt(7, s.getNoOfMonth());
                stmt.setString(8, s.getMonthName());
                stmt.setString(9, s.getGenDate());

                int i = stmt.executeUpdate();
                System.out.println(i + " Data inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(BillGenerate s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BillGenerate> getList() {
       List<BillGenerate> list = new ArrayList<>();
        try {
            BillGenerate p;
            PreparedStatement stmt = conn.prepareStatement("select * from bill");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new BillGenerate();
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setFatherOrMother(rs.getString(4));
                p.setMobile(rs.getString(5));
                p.setClassName(rs.getString(6));
                p.setMonthlyFee(rs.getInt(7));
                p.setNoOfMonth(rs.getInt(8));
                p.setMonthName(rs.getString(9));
                p.setGenDate(rs.getString(10));

                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Student getBillGenerateByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
