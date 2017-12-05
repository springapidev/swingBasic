package com.coderbd.mobile.dao;

import com.coderbd.mobile.domain.ProductSales;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public interface ProductSalesDao {

    void save(ProductSales s);

    void update(ProductSales s);

    void delete(int id);

    List<ProductSales> getList();

    ProductSales getProductSalesByID(int id);

}
