package com.coderbd.mobile.domain;

/**
 *
 * @author Rajaul Islam
 */
public class ProductSales {

    private int id;
    private String name;
    private String productCode;
    private int qty;
    private double unitPrice;
    private double totalPrice;
    private String category;
    private String salesDate;
    private String customername;
    private String customerMobile;

    public ProductSales() {
    }

    public ProductSales(String name, String productCode, int qty, double unitPrice, double totalPrice, String category, String salesDate, String customername, String customerMobile) {
        this.name = name;
        this.productCode = productCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.category = category;
        this.salesDate = salesDate;
        this.customername = customername;
        this.customerMobile = customerMobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

}
