package com.coderbd.mobile.domain;

/**
 *
 * @author Rajaul Islam
 */
public class Product {

    private int id;
    private String name;
    private String productCode;
    private double unitPrice;
    private int qty;
    private double totalPrice;
    private String category;
    private String purchaseDate;
 
    public Product() {
    }

    public Product(String name, String productCode, double unitPrice, int qty, double totalPrice, String category, String purchaseDate) {
        this.name = name;
        this.productCode = productCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.totalPrice = totalPrice;
         this.category = category;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

   
    public String getCategory() {
        return category;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

   
    
}
