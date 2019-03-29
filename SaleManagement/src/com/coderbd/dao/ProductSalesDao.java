package com.coderbd.dao;

import com.coderbd.pojo.ProductSales;
import java.util.List;

public interface ProductSalesDao {

    void createTable();

    void save(ProductSales ps);   

    ProductSales getProductSalesById(int id);

    ProductSales getProductSalesByProductCode(String code);   

    List<ProductSales> getList();

}
