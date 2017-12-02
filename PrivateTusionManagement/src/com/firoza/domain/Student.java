package com.firoza.domain;
/**
 *
 * @author Firoza Akter
 */
public class Student {

    private int id;
     private int studentID;
    private String name;
    private String fatherOrMother;
    private String mobile;
    private String className;
    private double monthlyFee;
    private int noOfMonth;
    private double totalFee;
    private String addmissionDate;

    public Student() {
    }

    public Student(int studentID, String name, String fatherOrMother, String mobile, String className, double monthlyFee, int noOfMonth, double totalFee, String addmissionDate) {
        this.studentID = studentID;
        this.name = name;
        this.fatherOrMother = fatherOrMother;
        this.mobile = mobile;
        this.className = className;
        this.monthlyFee = monthlyFee;
        this.noOfMonth = noOfMonth;
        this.totalFee = totalFee;
        this.addmissionDate = addmissionDate;
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

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getAddmissionDate() {
        return addmissionDate;
    }

    public void setAddmissionDate(String addmissionDate) {
        this.addmissionDate = addmissionDate;
    }

  
}
