package com.wansnow.ordering.entity;

public class OrderingList {
    private String id;//订单号，主键
    private String name;//订单人姓名
    private String tel;//订单电话号码
    private String appointmentTime;//订餐时间
    private String orderList;//json格式，菜单
    private Boolean isOrdering;//订单是否被确认

    public OrderingList(String id, String name, String tel, String appointmentTime, String orderList, Boolean isOrdering) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.appointmentTime = appointmentTime;
        this.orderList = orderList;
        this.isOrdering = isOrdering;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getOrdering() {
        return isOrdering;
    }

    public void setOrdering(Boolean ordering) {
        isOrdering = ordering;
    }
}
