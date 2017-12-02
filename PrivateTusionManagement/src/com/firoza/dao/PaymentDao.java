/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.dao;

import com.firoza.domain.Payment;
import com.firoza.domain.Student;
import java.util.List;

/**
 *
 * @author Rajail Islam
 */
public interface PaymentDao {
      void save(Payment s);

    void update(Payment s);

    void delete(int id);

    List<Payment> getList();

    Student getPaymentByID(int id);
}
