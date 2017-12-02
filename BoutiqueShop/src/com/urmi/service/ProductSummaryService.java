package com.urmi.service;

import com.urmi.conn.MySqlDbConnection;
import com.urmi.dao.ProductSummaryDao;
import com.urmi.domain.ProductSummary;
import com.urmi.domain.User;
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
 * @author J2EE-33
 */
public class ProductSummaryService implements ProductSummaryDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(ProductSummary s) {
        try {
            if (s.getPurchaseQty() != 0) {
                PreparedStatement stmt = conn.prepareStatement("insert into productsummary(product_code,purchase_qty,sold_qty, avilable_qty, product_name) values(?,?,?,?,?)");
                stmt.setString(1, s.getProductCode());
                stmt.setInt(2, s.getPurchaseQty());
                stmt.setInt(3, s.getSoldQty());
                stmt.setInt(4, s.getAvilableQty());
                stmt.setString(5, s.getProductName());

                int i = stmt.executeUpdate();
                System.out.println(i + " Product inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ProductSummary s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update productsummary SET purchase_qty=?, avilable_qty=? where id=?");

                stmt.setInt(1, s.getPurchaseQty());
                stmt.setInt(2, s.getAvilableQty());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    public void updateWhenSales(ProductSummary s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update productsummary SET sold_qty=?, avilable_qty=? where id=?");

                stmt.setInt(1, s.getSoldQty());
                stmt.setInt(2, s.getAvilableQty());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductSummary> getList() {
        List<ProductSummary> list = new ArrayList<>();
        try {
            ProductSummary p;
            PreparedStatement stmt = conn.prepareStatement("select * from productsummary");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new ProductSummary();
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getProductSummary(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductSummary getProductSummaryByProductCode(String productCode) {
        ProductSummary p = new ProductSummary();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from productsummary where product_code=?");
            stmt.setString(1, productCode);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public ProductSummary getProductSummaryById(int id) {
        ProductSummary p = new ProductSummary();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from productsummary where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductSummaryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
