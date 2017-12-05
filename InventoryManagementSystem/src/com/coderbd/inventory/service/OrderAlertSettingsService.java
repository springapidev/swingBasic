/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.inventory.service;

import com.coderbd.inventory.dao.OrderAlertSettingsDao;
import com.coderbd.inventory.domain.OrderAlertSettings;
import com.coderbd.inventory.domain.StockSummary;
import com.coderbd.mobile.conn.MySqlDbConnection;
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
public class OrderAlertSettingsService implements OrderAlertSettingsDao {

    Connection conn = MySqlDbConnection.getConnection();

    @Override
    public void save(OrderAlertSettings s) {
        try {
            if (s.getMinimumStockQty() != 0) {
                PreparedStatement stmt = conn.prepareStatement("insert into orderalertsettings(minimumStockQty,stockAlertPriority) values(?,?)");
                stmt.setInt(1, s.getMinimumStockQty());
                stmt.setString(2, s.getStockAlertPriority());

                int i = stmt.executeUpdate();
                System.out.println(i + " Data inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderAlertSettingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(OrderAlertSettings s) {
        try {
            if (s.getMinimumStockQty() != 0) {
                PreparedStatement stmt = conn.prepareStatement("update orderalertsettings set minimumStockQty=?,stockAlertPriority=? where minimumStockQty=?");
                stmt.setInt(1, s.getMinimumStockQty());
                stmt.setString(2, s.getStockAlertPriority());
                stmt.setInt(3, s.getId());
                int i = stmt.executeUpdate();
                System.out.println(i + " Data inserted");
            }
            // con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderAlertSettingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Override
    public List<OrderAlertSettings> getList() {
        List<OrderAlertSettings> list = new ArrayList<>();
        try {
            OrderAlertSettings p;
            PreparedStatement stmt = conn.prepareStatement("select * from orderalertsettings");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                p = new OrderAlertSettings();
                p.setId(rs.getInt(1));
                p.setMinimumStockQty(rs.getInt(2));
                p.setStockAlertPriority(rs.getString(3));
              list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderAlertSettingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
