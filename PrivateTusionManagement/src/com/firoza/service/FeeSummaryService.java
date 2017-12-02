package com.firoza.service;

import com.firoza.conn.MySqlDbConnection;
import com.firoza.domain.FeeSummary;
import com.firoza.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.firoza.dao.FeeSummaryDao;

/**
 *
 * @author J2EE-33
 */
public class FeeSummaryService implements FeeSummaryDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(FeeSummary s) {
        try {
            if (s.getStudentID() != 0) {

                PreparedStatement stmt = conn.prepareStatement("insert into feesummary(student_id, student_name,totalPaybleFee,paidFee, dueFee) values(?,?,?,?,?)");
                stmt.setInt(1, s.getStudentID());
                stmt.setString(2, s.getName());
                stmt.setDouble(3, s.getTotalPaybleFee());
                stmt.setDouble(4, s.getPaidFee());
                stmt.setDouble(5, s.getDueFee());

                int i = stmt.executeUpdate();
                System.out.println(i + " Data inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(FeeSummary s) {
        try {
            if (s.getId() != 0) {//totalPaybleFee,paidFee, dueFee
                PreparedStatement stmt = conn.prepareStatement("update feesummary SET totalPaybleFee=?, dueFee=? where id=?");

                stmt.setDouble(1, s.getTotalPaybleFee());
                stmt.setDouble(2, s.getDueFee());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeeSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateWhenPay(FeeSummary s) {
        try {
            if (s.getId() != 0) {////totalPaybleFee,paidFee, dueFee
                PreparedStatement stmt = conn.prepareStatement("update feesummary SET paidFee=?, dueFee=? where id=?");

                stmt.setDouble(1, s.getPaidFee());
                stmt.setDouble(2, s.getDueFee());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeeSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FeeSummary> getList() {
        List<FeeSummary> list = new ArrayList<>();
        try {
            FeeSummary p;
            PreparedStatement stmt = conn.prepareStatement("select * from feesummary");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new FeeSummary();
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setTotalPaybleFee(rs.getInt(4));
                p.setPaidFee(rs.getInt(5));
                p.setDueFee(rs.getInt(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeeSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getProductSummary(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FeeSummary getFeeSummaryByStudentID(int studentID) {
        FeeSummary p = new FeeSummary();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from feesummary where student_id=?");
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setTotalPaybleFee(rs.getInt(4));
                p.setPaidFee(rs.getInt(5));
                p.setDueFee(rs.getDouble(6));

            }

        } catch (SQLException ex) {
            Logger.getLogger(FeeSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public FeeSummary getFeeSummaryById(int id) {
        FeeSummary p = new FeeSummary();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from feesummary where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setStudentID(rs.getInt(2));
                p.setName(rs.getString(3));
                p.setTotalPaybleFee(rs.getInt(4));
                p.setPaidFee(rs.getInt(5));
                p.setDueFee(rs.getDouble(6));

            }

        } catch (SQLException ex) {
            Logger.getLogger(FeeSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
