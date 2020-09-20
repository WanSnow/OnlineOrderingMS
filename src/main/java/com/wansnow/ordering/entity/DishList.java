package com.wansnow.ordering.entity;

import java.io.Serializable;

public class DishList implements Serializable {
    private static final long serialVersionUID = -9033401295157820425L;

    private String dishId;
    private String shopId;
    private String dishName;
    private String price;
    private String dishImage;

    public DishList(String dishId, String shopId, String dishName, String price, String dishImage) {
        this.dishId = dishId;
        this.shopId = shopId;
        this.dishName = dishName;
        this.price = price;
        this.dishImage = dishImage;
    }

    public DishList() {
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }
}
