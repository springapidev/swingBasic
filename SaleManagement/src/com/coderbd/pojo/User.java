package com.coderbd.pojo;

public class User {

    private int id;
    private String fullName;
    private String userName;
    private String password;
    private String mobile;
    private Role role;

    public User() {
    }

    public User(String fullName, String userName, String password, String mobile, Role role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    public User(int id, String fullName, String userName, String password, String mobile, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
