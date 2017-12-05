/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.inventory.domain;

/**
 *
 * @author Rajaul Islam
 */
public class StatisticsForDashBoard {

    private String itemName;
    private String productCode;
    private String brandOrCategory;
    private int totalItemIn;
    private int totalItemOut;
    private int totalItemAvailable;

    public StatisticsForDashBoard() {
    }

    public StatisticsForDashBoard(String itemName, String productCode, String brandOrCategory, int totalItemIn, int totalItemOut, int totalItemAvailable) {
        this.itemName = itemName;
        this.productCode = productCode;
        this.brandOrCategory = brandOrCategory;
        this.totalItemIn = totalItemIn;
        this.totalItemOut = totalItemOut;
        this.totalItemAvailable = totalItemAvailable;
    }

    public StatisticsForDashBoard(String brandOrCategory) {
        this.brandOrCategory = brandOrCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getBrandOrCategory() {
        return brandOrCategory;
    }

    public int getTotalItemIn() {
        return totalItemIn;
    }

    public int getTotalItemOut() {
        return totalItemOut;
    }

    public int getTotalItemAvailable() {
        return totalItemAvailable;
    }

}
