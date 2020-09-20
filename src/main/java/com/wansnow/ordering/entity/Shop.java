package com.wansnow.ordering.entity;

import java.io.Serializable;

public class Shop implements Serializable {
    private static final long serialVersionUID = 3081221007912536739L;

    private String shopId;
    private String pwd;
    private String shopName;
    private String ownerName;
    private String tel;
    private String addr;
    private boolean isVerify;

    public Shop(String shopId, String pwd, String shopName, String ownerName, String tel, String addr, boolean isVerify) {
        this.shopId = shopId;
        this.pwd = pwd;
        this.shopName = shopName;
        this.ownerName = ownerName;
        this.tel = tel;
        this.addr = addr;
        this.isVerify = isVerify;
    }

    public Shop() {
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
