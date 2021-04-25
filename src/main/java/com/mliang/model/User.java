package com.mliang.model;

import java.util.Date;

public class User {
    private String id;
    private String email;
    private String mobile;
    private String username;
    private String role;
    private String password;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public User(String id, String email, String mobile, String username, String role, Date createTime) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.role = role;
        this.createTime = createTime;
    }

    public User(String id, String email, String mobile, String username, String role) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
