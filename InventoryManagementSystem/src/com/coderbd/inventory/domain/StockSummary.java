package com.coderbd.inventory.domain;

/**
 *
 * @author Rajaul Islam
 */
public class StockSummary {

    private int id;
    private String productCode;
    private int purchaseQty;
    private int soldQty;
    private int avilableQty;
    private String productName;

    public StockSummary() {
    }

    public StockSummary(int purchaseQty, int avilableQty) {
        this.purchaseQty = purchaseQty;
        this.avilableQty = avilableQty;
    }

    public StockSummary(String productCode, int purchaseQty, int soldQty, int avilableQty, String productName) {
        this.productCode = productCode;
        this.purchaseQty = purchaseQty;
        this.soldQty = soldQty;
        this.avilableQty = avilableQty;
        this.productName = productName;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
