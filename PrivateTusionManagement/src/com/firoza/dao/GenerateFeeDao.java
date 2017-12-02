/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.dao;

import com.firoza.domain.BillGenerate;
import com.firoza.domain.Student;
import java.util.List;

/**
 *
 * @author J2EE-33
 */
public interface GenerateFeeDao {

    void save(BillGenerate s);

    void update(BillGenerate s);

    void delete(int id);

    List<BillGenerate> getList();

    Student getBillGenerateByID(int id);

}
