/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.urmi.dao;

import com.urmi.domain.Product;
import com.urmi.domain.User;
import java.util.List;

/**
 *
 * @author J2EE-33
 */
public interface ProductDao {

    void save(Product s);

    void update(Product s);

    void delete(int id);

    List<Product> getList();

    Product getProductByID(int id);

}
