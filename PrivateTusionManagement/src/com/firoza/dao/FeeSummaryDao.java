/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.dao;

import com.firoza.domain.FeeSummary;
import com.firoza.domain.User;
import java.util.List;

/**
 *
 * @author Firoza Akter
 */
public interface FeeSummaryDao {

    void save(FeeSummary s);

    void update(FeeSummary s);

    void delete(int id);

    List<FeeSummary> getList();

    User getProductSummary(int id);

    public FeeSummary getFeeSummaryByStudentID(int studentID);

    public FeeSummary getFeeSummaryById(int id);
     public void updateWhenPay(FeeSummary s);
}
