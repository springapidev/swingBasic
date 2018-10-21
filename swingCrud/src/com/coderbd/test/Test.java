package com.coderbd.test;

import com.coderbd.dao.StudentDao;
import com.coderbd.daoImpl.StudentDaoImpl;
import com.coderbd.domain.Student;

public class Test {

    public static void main(String[] args) {
        String sq = "create table student(id number(5) primary key,name varchar2(55))";
        StudentDao obj = new StudentDaoImpl();
        // obj.createTable(sq);
        Student student = new Student();
        student.setId(2);
        student.setName("Ajad");
        // obj.insert(student);
        //obj.update(student);

        Student st = obj.getById(1);
        System.out.println("ID::: " + st.getId() + " Name::: " + st.getName());

        obj.delete(student);
        for (Student s : obj.getStudents()) {
            System.out.println("ID: " + s.getId() + " Name: " + s.getName());
        }
    }

}
