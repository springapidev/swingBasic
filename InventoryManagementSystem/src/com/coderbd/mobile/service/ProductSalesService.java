package com.coderbd.mobile.service;

import com.coderbd.mobile.conn.MySqlDbConnection;
import com.coderbd.mobile.dao.ProductSalesDao;
import com.coderbd.mobile.domain.ProductSales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rajaul Islam
 */
public class ProductSalesService implements ProductSalesDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(ProductSales s) {
        try {
            if (s.getName() != null) {
                PreparedStatement stmt = conn.prepareStatement("insert into sales_details(product_name,product_code,qty, unit_price,total_price,category, sales_date,customer_name, customer_mobile ) values(?,?,?,?,?,?,?,?,?)");

                stmt.setString(1, s.getName());
                stmt.setString(2, s.getProductCode());
                stmt.setInt(3, s.getQty());
                stmt.setDouble(4, s.getUnitPrice());
                stmt.setDouble(5, s.getTotalPrice());
                stmt.setString(6, s.getCategory());
                stmt.setString(7, s.getSalesDate());
                stmt.setString(8, s.getCustomername());
                stmt.setString(9, s.getCustomerMobile());

                int i = stmt.executeUpdate();
                System.out.println(i + " Product inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveList(Set<ProductSales> productSales) {

        if (productSales.size() > 0) {
            productSales.forEach((s) -> {
                PreparedStatement stmt;
                try {
                    stmt = conn.prepareStatement("insert into sales_details(product_name,product_code,qty, unit_price,total_price,sales_date, customer_name, customer_mobile ) values(?,?,?,?,?,?,?,?)");
                    stmt.setString(1, s.getName());
                    stmt.setString(2, s.getProductCode());
                    stmt.setInt(3, s.getQty());
                    stmt.setDouble(4, s.getUnitPrice());
                    stmt.setDouble(5, s.getTotalPrice());
                    stmt.setString(6, s.getSalesDate());
                    stmt.setString(7, s.getCustomername());
                    stmt.setString(8, s.getCustomerMobile());
                    int i = stmt.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(ProductSalesService.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        }
        System.out.println(productSales.size() + " records inserted");
    }

    @Override
    public void update(ProductSales s) {
        try {
            if (s.getId() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update sales_details SET name=?,product_code=?,unitPrice=?,qty=?, totalPrice=?, purchase_date=?  where id=?");

                stmt.setString(1, s.getName());
                stmt.setString(2, s.getProductCode());
                stmt.setInt(3, s.getQty());
                stmt.setDouble(4, s.getUnitPrice());

                stmt.setDouble(5, s.getTotalPrice());
                stmt.setString(6, s.getSalesDate());
                stmt.setString(6, s.getSalesDate());
                stmt.setInt(7, s.getId());
                int i = stmt.executeUpdate();

                System.out.println(i + " record Updated");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (id != 0) {
                PreparedStatement stmt = conn.prepareStatement("delete from sales_details where id=?");

                stmt.setInt(1, id);

                int i = stmt.executeUpdate();
                System.out.println(i + " record Deleted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductSalesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ProductSales> getList() {
        List<ProductSales> list = new ArrayList<>();
        try {
            ProductSales p;
            PreparedStatement stmt = conn.prepareStatement("select * from sales_details");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new ProductSales();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setProductCode(rs.getString(3));
                p.setQty(rs.getInt(4));
                p.setUnitPrice(rs.getInt(5));
                p.setTotalPrice(rs.getInt(6));
                p.setSalesDate(rs.getString(7));
                p.setCustomername(rs.getString(8));
                p.setCustomerMobile(rs.getString(9));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ProductSales getProductSalesByID(int id) {
        ProductSales p = new ProductSales();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from sales_details where id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setProductCode(rs.getString(3));
                p.setQty(rs.getInt(4));
                p.setUnitPrice(rs.getInt(5));
                p.setTotalPrice(rs.getInt(6));
                p.setSalesDate(rs.getString(7));
                p.setCustomername(rs.getString(8));
                p.setCustomerMobile(rs.getString(9));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
