package com.coderbd.inventory.domain;

/**
 *
 * @author Rajaul Islam
 */
public class ItemsEligibleForOder {

    private int id;
    private int itemID;
    private String productName;
    private String productCode;
    private int purchaseQty;
    private int soldQty;
    private int avilableQty;
    private int orderQty;
    private String orderStatus;

    public ItemsEligibleForOder() {
    }

    public ItemsEligibleForOder(int id, String productName, String productCode, int purchaseQty, int soldQty, int avilableQty, int orderQty, String orderStatus) {
        this.id = id;
        this.productName = productName;
        this.productCode = productCode;
        this.purchaseQty = purchaseQty;
        this.soldQty = soldQty;
        this.avilableQty = avilableQty;
        this.orderQty = orderQty;
        this.orderStatus = orderStatus;
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(int purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public int getAvilableQty() {
        return avilableQty;
    }

    public void setAvilableQty(int avilableQty) {
        this.avilableQty = avilableQty;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
