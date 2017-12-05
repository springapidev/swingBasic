package com.coderbd.inventory.service;

import com.coderbd.inventory.dao.ItemsEligibleForOderSummaryDao;
import com.coderbd.inventory.domain.ItemsEligibleForOder;
import com.coderbd.mobile.conn.MySqlDbConnection;
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
public class ItemsEligibleForOderService implements ItemsEligibleForOderSummaryDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void saveList(Set<ItemsEligibleForOder> items) {
        if (items.size() > 0) {
            for (ItemsEligibleForOder item : items) {
                PreparedStatement stmt;
                try {
                    stmt = conn.prepareStatement("insert into itemseligibleforoder(product_code,purchase_qty,sold_qty, avilable_qty, product_name,orderQty,orderStatus) values(?,?,?,?,?,?,?)");
                    stmt.setString(1, item.getProductCode());
                    stmt.setInt(2, item.getPurchaseQty());
                    stmt.setInt(3, item.getSoldQty());
                    stmt.setInt(4, item.getAvilableQty());
                    stmt.setString(5, item.getProductName());
                    stmt.setInt(6, item.getOrderQty());
                    stmt.setString(7, item.getOrderStatus());
                    int i = stmt.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        System.out.println(items.size() + " records inserted");
    }

    @Override
    public void updateList(Set<ItemsEligibleForOder> items) {
        if (items.size() > 0) {
            for (ItemsEligibleForOder item : items) {
                PreparedStatement stmt;
                try {
                    stmt = conn.prepareStatement("update itemseligibleforoder set orderQty=?,orderStatus=? where id=?");

                    stmt.setInt(1, item.getOrderQty());
                    stmt.setString(2, item.getOrderStatus());
                    stmt.setInt(3, item.getId());
                    int i = stmt.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        //  System.out.println(items.size() + " records inserted");
    }

    @Override
    public List<ItemsEligibleForOder> getList() {
        List<ItemsEligibleForOder> list = new ArrayList<>();
        try {
            ItemsEligibleForOder p;
            PreparedStatement stmt = conn.prepareStatement("select * from itemseligibleforoder");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new ItemsEligibleForOder();
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));
                p.setOrderQty(rs.getInt(7));
                p.setOrderStatus(rs.getString(8));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ItemsEligibleForOder getItemEligibleForOderByOrderStatus(String orderStatus) {
        ItemsEligibleForOder p = new ItemsEligibleForOder();
        try {

            PreparedStatement stmt = conn.prepareStatement("select * from itemseligibleforoder where orderStatus=?");
            stmt.setString(1, orderStatus);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));
                p.setOrderQty(rs.getInt(7));
                p.setOrderStatus(rs.getString(8));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public ItemsEligibleForOder getItemsEligibleForOderByOrderStatusAndProductCode(String productCode, String orderStatus) {
        ItemsEligibleForOder p = new ItemsEligibleForOder();
        try {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM itemseligibleforoder where product_code=? and orderStatus=?");
            stmt.setString(1, productCode);
            stmt.setString(2, orderStatus);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));
                p.setOrderQty(rs.getInt(7));
                p.setOrderStatus(rs.getString(8));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<ItemsEligibleForOder> getItemsEligibleForOderByOrderStatus(String orderStatus) {
        List<ItemsEligibleForOder> list = new ArrayList<>();
        try {
            ItemsEligibleForOder p;
            PreparedStatement stmt = conn.prepareStatement("select * from itemseligibleforoder where orderStatus=?");
            stmt.setString(1, orderStatus);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new ItemsEligibleForOder();
                p.setId(rs.getInt(1));
                p.setProductCode(rs.getString(2));
                p.setPurchaseQty(rs.getInt(3));
                p.setSoldQty(rs.getInt(4));
                p.setAvilableQty(rs.getInt(5));
                p.setProductName(rs.getString(6));
                p.setOrderQty(rs.getInt(7));
                p.setOrderStatus(rs.getString(8));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemsEligibleForOderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
