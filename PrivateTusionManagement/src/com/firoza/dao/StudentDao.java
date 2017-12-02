/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.dao;

import com.firoza.domain.Student;
import java.util.List;

/**
 *
 * @author Firoza Akter
 */
public interface StudentDao {

    void save(Student s);

    void update(Student s);

    void delete(int id);

    List<Student> getList();

    Student getStudentByID(int id);

}
