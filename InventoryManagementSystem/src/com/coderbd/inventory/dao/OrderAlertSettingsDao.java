/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.inventory.dao;

import com.coderbd.inventory.domain.OrderAlertSettings;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public interface OrderAlertSettingsDao {

    void save(OrderAlertSettings s);

    void update(OrderAlertSettings s);

     public List<OrderAlertSettings> getList();

}
