package com.urmi.dao;

import com.urmi.domain.Product;
import com.urmi.domain.ProductSales;
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
