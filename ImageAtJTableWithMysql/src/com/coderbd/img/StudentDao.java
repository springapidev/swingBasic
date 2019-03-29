/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.img;

import java.io.File;
import java.util.List;

/**
 *
 * @author rajaul
 */
public interface StudentDao {
    void save(Student student, File file);
    List<Student> getList();
}
