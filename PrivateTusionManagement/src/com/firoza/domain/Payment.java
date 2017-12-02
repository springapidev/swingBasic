
package com.firoza.domain;

/**
 *
 * @author Firoza Akter
 */
public class Payment {

    private int id;
    private int studentID;
    private String name;
    private String fatherOrMother;
    private String mobile;
    private String className;
    private double monthlyFee;
    private int noOfMonth;
    private String monthName;
    private double totalPaidAmount;
    private String payDate;

    public Payment() {
    }

    public Payment(int studentID, String name, String fatherOrMother, String mobile, String className, double monthlyFee, int noOfMonth, String monthName, double totalPaidAmount, String payDate) {
        this.studentID = studentID;
        this.name = name;
        this.fatherOrMother = fatherOrMother;
        this.mobile = mobile;
        this.className = className;
        this.monthlyFee = monthlyFee;
        this.noOfMonth = noOfMonth;
        this.monthName = monthName;
        this.totalPaidAmount = totalPaidAmount;
        this.payDate = payDate;
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

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
    
    
}
