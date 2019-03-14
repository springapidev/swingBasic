package com.coderbd.dao;

import com.coderbd.pojo.Product;
import java.util.List;

public interface ProductDao {

    void createTable();

    void save(Product p);

    void update(Product p);

    Product getProductById(int id);

    Product getProductByProductCode(String code);

    void delete(int id);

    List<Product> getList();

}
