/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderbd.img;

import java.sql.Blob;

/**
 *
 * @author rajaul
 */
public class Student {

    private int id;
    private String name;
    private byte[] photo;
    private String filePath;
    private String fileName;

    public Student() {
    }

    public Student(String name,String filePath, String fileName) {
        this.name = name;        
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public Student(int id, String name, byte[] photo, String filePath, String fileName) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

   


  

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
