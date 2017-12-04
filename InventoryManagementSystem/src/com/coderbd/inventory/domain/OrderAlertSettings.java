package com.coderbd.inventory.domain;

/**
 *
 * @author Rajaul Islam
 */
public class OrderAlertSettings {

    private int id;
    private int minimumStockQty;
    private String stockAlertPriority;

    public OrderAlertSettings() {
    }

    public OrderAlertSettings(int minimumStockQty, String stockAlertPriority) {
        this.minimumStockQty = minimumStockQty;
        this.stockAlertPriority = stockAlertPriority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinimumStockQty() {
        return minimumStockQty;
    }

    public void setMinimumStockQty(int minimumStockQty) {
        this.minimumStockQty = minimumStockQty;
    }

    public String getStockAlertPriority() {
        return stockAlertPriority;
    }

    public void setStockAlertPriority(String stockAlertPriority) {
        this.stockAlertPriority = stockAlertPriority;
    }

}
