/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.mobile.dao;

import com.coderbd.mobile.domain.Product;
import java.util.List;

/**
 *
 * @author Rajaul Islam
 */
public interface ProductDao {

    void save(Product s);

    void update(Product s);

    void delete(int id);

    List<Product> getList();

    Product getProductByID(int id);

}
