package com.coderbd.daoImpl;

import com.coderbd.conn.CustomDBConnection;
import com.coderbd.dao.SummaryDao;
import com.coderbd.pojo.Product;
import com.coderbd.pojo.Summary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SummaryDaoImpl implements SummaryDao {

    Connection conn = CustomDBConnection.getDBConnection();

    @Override
    public void createTable() {
        String sql = "create table IF NOT EXISTS summary(id int(11) auto_increment primary key, product_name varchar(50),product_code varchar(30) unique,total_qty int(11),sold_qty int(11),available_qty int(11),  p_id int(11),FOREIGN KEY (p_id) REFERENCES product(id))";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    product_name varchar(50),product_code varchar(30) unique,total_qty int(11),sold_qty int(11),available_qty int(11),  p_id int(11),FOREIGN KEY (p_id) REFERENCES product(id)
     */
    @Override
    public void save(Summary s) {
        String sql = "insert into summary(product_name,product_code,total_qty,sold_qty,available_qty,p_id) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, s.getProductName());
            pstm.setString(2, s.getProductCode());
            pstm.setInt(3, s.getTotalQty());
            pstm.setInt(4, s.getSoldQty());
            pstm.setInt(5, s.getAvailableQty());
            pstm.setInt(6, s.getProduct().getId());
            pstm.executeUpdate();
            System.out.println("Insert success!");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Summary s) {
        String sql = "update summary set total_qty=?,sold_qty=?,available_qty=? where product_code=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
           
            pstm.setInt(1, s.getTotalQty());
            pstm.setInt(2, s.getSoldQty());
            pstm.setInt(3, s.getAvailableQty());
            pstm.setString(4, s.getProductCode());          
            pstm.executeUpdate();
            System.out.println("Update success!");
        } catch (SQLException ex) {
            Logger.getLogger(SummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Summary getSummaryByProductCode(String code) {
        Summary summary = null;
        String sql = "select * from summary where product_code=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                summary = new Summary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Product(rs.getInt(7)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(SummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return summary;
    }

    @Override
    public List<Summary> getList() {
        List<Summary> list = new ArrayList<>();
        String sql = "select * from summary";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Summary summary = new Summary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), new Product(rs.getInt(7)));
                list.add(summary);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SummaryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
