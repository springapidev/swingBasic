package com.coderbd.dao;

import com.coderbd.pojo.ProductCategory;
import java.util.List;

public interface ProductCategoryDao {

    void createTable();

    void save(ProductCategory pc);

    void update(ProductCategory pc);

    ProductCategory getProductCategoryById(int id);

    ProductCategory getProductCategoryBycategoryName(String catName);

    void delete(int id);

    List<ProductCategory> getList();

}
