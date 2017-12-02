package com.parvez.dao;

import com.parvez.domain.Product;
import com.parvez.domain.ProductSales;
import java.util.List;

/**
 *
 * @author J2EE-33
 */
public interface ProductSalesDao {

    void save(ProductSales s);

    void update(ProductSales s);

    void delete(int id);

    List<ProductSales> getList();

    ProductSales getProductSalesByID(int id);

}
