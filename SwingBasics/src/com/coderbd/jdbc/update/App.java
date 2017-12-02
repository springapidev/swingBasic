/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.jdbc.update;

import com.coderbd.jdbc.domain.Student;

/**
 *
 * @author Rajail Islam
 */
public class App {
    public static void main(String[] args) {
        Student s=new Student("Shohan", 25);
        s.setId(2);
        TestUpdate.update(s);
    }
}
