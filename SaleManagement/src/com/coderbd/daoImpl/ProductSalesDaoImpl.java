package com.coderbd.daoImpl;

import com.coderbd.conn.CustomDBConnection;
import com.coderbd.dao.ProductSalesDao;
import com.coderbd.pojo.Product;
import com.coderbd.pojo.ProductSales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductSalesDaoImpl implements ProductSalesDao {

    Connection conn = CustomDBConnection.getDBConnection();

    public static void main(String[] args) {
        ProductSalesDaoImpl obj = new ProductSalesDaoImpl();
        obj.createTable();
    }

    @Override
    public void createTable() {
        String sql = "create table IF NOT EXISTS sales(id int(11) auto_increment primary key, product_name varchar(50),product_code varchar(30),qty int(11),unit_price double,total_price double, sales_date date, p_id int(11),FOREIGN KEY (p_id) REFERENCES product(id))";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();
            System.out.println("Table Created!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(ProductSales ps) {
        String sql = "insert into sales(product_name,product_code,qty,unit_price,total_price,sales_date,p_id) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ps.getProductName());
            pstm.setString(2, ps.getProductCode());
            pstm.setInt(3, ps.getQty());
            pstm.setDouble(4, ps.getUnitPrice());
            pstm.setDouble(5, ps.getTotalPrice());
            pstm.setDate(6, ps.getSalesDate());
            pstm.setInt(7, ps.getProduct().getId());
            pstm.executeUpdate();
            System.out.println("Insert success!");
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ProductSales getProductSalesById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductSales getProductSalesByProductCode(String code) {
        ProductSales product = null;
        String sql = "select * from sales where product_code=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                product = new ProductSales(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getDate(7), new Product(rs.getInt(8)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public List<ProductSales> getList() {
        List<ProductSales> list = new ArrayList();
        String sql = "select * from sales";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductSales p = new ProductSales(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getDate(7), new Product(rs.getInt(8)));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
