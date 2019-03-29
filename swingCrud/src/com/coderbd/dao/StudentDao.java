package com.coderbd.dao;

import com.coderbd.domain.Student;
import java.util.List;

public interface StudentDao {

    void createTable(String sql);

    void insert(Student s);

    void update(Student s);

    void delete(Student s);

    Student getById(int id);

    List<Student> getStudents();
}
