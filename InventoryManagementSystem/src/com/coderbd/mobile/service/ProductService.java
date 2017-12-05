package com.coderbd.mobile.service;

import com.coderbd.mobile.conn.MySqlDbConnection;
import com.coderbd.mobile.dao.ProductDao;
import com.coderbd.mobile.domain.Product;
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
 * @author Rajaul Islam
 */
public class ProductService implements ProductDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(Product s) {
        try {
            if (s.getName() != null) {
                PreparedStatement stmt = conn.prepareStatement("insert into product(name,product_code,unitPrice, qty,totalPrice,category, purchase_date) values(?,?,?,?,?,?,?)");
                stmt.setString(1, s.getName());
                stmt.setString(2, s.getProductCode());
                stmt.setDouble(3, s.getUnitPrice());
                stmt.setInt(4, s.getQty());
                stmt.setDouble(5, s.getTotalPrice());
                stmt.setString(6, s.getCategory());
                stmt.setString(7, s.getPurchaseDate());

                int i = stmt.executeUpdate();
                System.out.println(i + " Product inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update product SET name=?,product_code=?,unitPrice=?,qty=?, totalPrice=?, category=?, purchase_date=?  where id=?");

                stmt.setString(1, s.getName());
                stmt.setString(2, s.getProductCode());
                stmt.setDouble(3, s.getUnitPrice());
                stmt.setInt(4, s.getQty());
                stmt.setDouble(5, s.getTotalPrice());
                  stmt.setString(6, s.getCategory());
                stmt.setString(7, s.getPurchaseDate());
                stmt.setInt(8, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from product where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " record Deleted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Product> getList() {
        List<Product> list = new ArrayList<>();
        try {
            Product p;
            PreparedStatement stmt = conn.prepareStatement("select * from product");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setProductCode(rs.getString(3));
                p.setUnitPrice(rs.getInt(4));
                p.setQty(rs.getInt(5));
                p.setTotalPrice(rs.getInt(6));
                p.setCategory(rs.getString(7));
                p.setPurchaseDate(rs.getString(8));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Product getProductByID(int id) {
        Product p = new Product();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from product where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setProductCode(rs.getString(3));
                p.setUnitPrice(rs.getInt(4));
                p.setQty(rs.getInt(5));
                p.setTotalPrice(rs.getInt(6));
                p.setCategory(rs.getString(7));
                p.setPurchaseDate(rs.getString(8));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
