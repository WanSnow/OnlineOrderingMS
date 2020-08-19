package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.DishList;

import java.util.List;

public interface DishListDaoImpl {
    List<DishList> findDishListByShopId(String shopId);
    DishList findDishListByDishId(String dishId);
    int insertDishList(DishList dishList);
    int updateDishList(DishList dishList);
    int deleteDishList(String dishId);
}
