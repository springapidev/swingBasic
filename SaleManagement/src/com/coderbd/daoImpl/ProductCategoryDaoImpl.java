package com.coderbd.daoImpl;

import com.coderbd.conn.CustomDBConnection;
import com.coderbd.dao.ProductCategoryDao;
import com.coderbd.pojo.ProductCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

    Connection conn = CustomDBConnection.getDBConnection();

    @Override
    public void createTable() {
        String sql = "create table IF NOT EXISTS product_category(id int(5) auto_increment primary key, cat_name varchar(20) unique)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(ProductCategory pc) {
        String sql = "insert into product_category(cat_name) values(?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, pc.getCatName());
            pstm.executeUpdate();
            System.out.println("Insert success!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ProductCategory pc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductCategory getProductCategoryById(int id) {
        ProductCategory pc = new ProductCategory();
        String sql = "select * from product_category where id=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                pc.setId(rs.getInt(1));
                pc.setCatName(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pc;
    }

    @Override
    public ProductCategory getProductCategoryBycategoryName(String catName) {
        ProductCategory pc = new ProductCategory();
        String sql = "select * from product_category where cat_name=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, catName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                pc.setId(rs.getInt(1));
                pc.setCatName(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pc;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductCategory> getList() {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "select * from product_category";
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCategory pc = new ProductCategory(rs.getInt(1), rs.getString(2));
                list.add(pc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
