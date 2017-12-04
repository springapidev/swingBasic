package com.coderbd.inventory.others;

import com.coderbd.inventory.domain.OrderAlertSettings;
import com.coderbd.inventory.enums.StockAlertPriority;
import com.coderbd.inventory.service.OrderAlertSettingsService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public class OrderAlertSettingsApp {

    public static void main(String[] args) {
        OrderAlertSettingsService oas = new OrderAlertSettingsService();
        List<OrderAlertSettings> list = new ArrayList();
        list.add(new OrderAlertSettings(5, StockAlertPriority.URGENT.toString()));
        list.add(new OrderAlertSettings(10, StockAlertPriority.HIGH.toString()));
        list.add(new OrderAlertSettings(20, StockAlertPriority.LOW.toString()));
        list.add(new OrderAlertSettings(21, StockAlertPriority.NORMAL.toString()));
        for (OrderAlertSettings s : list) {
            oas.save(s);
        }
    }
}
