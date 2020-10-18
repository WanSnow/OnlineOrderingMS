package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.DishList;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DishListDaoImpl {
    List<DishList> findDishListByShopId(String shopId);
    @Select(value = "SELECT * FROM dish_list_info WHERE dish_id=#{dishId}")
    DishList findDishListByDishId(String dishId);
    int insertDishList(DishList dishList);
    @Update(value = "UPDATE t_user SET dish_name=#{dishList.dishName},dish_price=#{dishList.prise} WHERE dish_id=#{dishList.dishId}")
    int updateDishList(DishList dishList);
    int deleteDishList(String dishId);
}
