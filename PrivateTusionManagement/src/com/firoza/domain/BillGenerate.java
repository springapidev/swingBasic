/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.firoza.domain;

/**
 *
 * @author Firoza Akter
 */
public class BillGenerate {

    private int id;
    private int studentID;
    private String name;
    private String fatherOrMother;
    private String mobile;
    private String className;
    private double monthlyFee;
    private int noOfMonth;
    private String monthName;
    private String genDate;

    public BillGenerate() {
    }

    public BillGenerate(int studentID, String name, String fatherOrMother, String mobile, String className, double monthlyFee, int noOfMonth, String monthName, String genDate) {
        this.studentID = studentID;
        this.name = name;
        this.fatherOrMother = fatherOrMother;
        this.mobile = mobile;
        this.className = className;
        this.monthlyFee = monthlyFee;
        this.noOfMonth = noOfMonth;
        this.monthName = monthName;
        this.genDate = genDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherOrMother() {
        return fatherOrMother;
    }

    public void setFatherOrMother(String fatherOrMother) {
        this.fatherOrMother = fatherOrMother;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getNoOfMonth() {
        return noOfMonth;
    }

    public void setNoOfMonth(int noOfMonth) {
        this.noOfMonth = noOfMonth;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }
    
    
    
}
