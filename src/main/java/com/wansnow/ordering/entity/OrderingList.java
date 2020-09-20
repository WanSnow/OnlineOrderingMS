package com.wansnow.ordering.entity;

import java.io.Serializable;

public class OrderingList implements Serializable {
    private static final long serialVersionUID = 6250205198730886626L;

    private String orderingId;//订单号，主键
    private String shopId;//店铺id
    private String shopName;//店铺名字
    private String email;//订单人邮箱
    private String realName;//订单人姓名
    private String tel;//订单电话号码
    private String appointmentTime;//订餐时间
    private String orderList;//json格式，菜单
    private boolean isOrdering;//订单是否被确认

    public OrderingList(String orderingId, String shopId, String shopName, String email, String realName, String tel, String appointmentTime, String orderList, Boolean isOrdering) {
        this.orderingId = orderingId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.email = email;
        this.realName = realName;
        this.tel = tel;
        this.appointmentTime = appointmentTime;
        this.orderList = orderList;
        this.isOrdering = isOrdering;
    }

    public OrderingList() {
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(String orderingId) {
        this.orderingId = orderingId;
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

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public boolean isOrdering() {
        return isOrdering;
    }

    public void setOrdering(boolean ordering) {
        isOrdering = ordering;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
