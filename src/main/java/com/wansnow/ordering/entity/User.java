package com.wansnow.ordering.entity;

public class User {
    private String email;//邮箱，主键
    private String pwd;//密码
    private String username;//用户名
    private String realName;//真实姓名
    private String tel;//电话号码

    public User(String email, String pwd, String username, String realName, String tel) {
        this.email = email;
        this.pwd = pwd;
        this.username = username;
        this.realName = realName;
        this.tel = tel;
    }

    public User() {
        new User(null, null, null, null, null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
