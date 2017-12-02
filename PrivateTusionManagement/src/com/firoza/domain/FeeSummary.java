package com.firoza.domain;

/**
 *
 * @author Firoza Akter
 */
public class FeeSummary {

    private int id;
    private int studentID;
    private String name;
    private double totalPaybleFee;
    private double paidFee;
    private double dueFee;

    public FeeSummary() {
    }

    public FeeSummary(double paidFee, double dueFee) {
        this.paidFee = paidFee;
        this.dueFee = dueFee;
    }

    public FeeSummary(int studentID, String name, double totalPaybleFee, double paidFee, double dueFee) {
        this.studentID = studentID;
        this.name = name;
        this.totalPaybleFee = totalPaybleFee;
        this.paidFee = paidFee;
        this.dueFee = dueFee;
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

    public double getTotalPaybleFee() {
        return totalPaybleFee;
    }

    public void setTotalPaybleFee(double totalPaybleFee) {
        this.totalPaybleFee = totalPaybleFee;
    }

    public double getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(double paidFee) {
        this.paidFee = paidFee;
    }

    public double getDueFee() {
        return dueFee;
    }

    public void setDueFee(double dueFee) {
        this.dueFee = dueFee;
    }

}
