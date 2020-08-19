package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.DishListDao;
import com.wansnow.ordering.dao.ShopDao;
import com.wansnow.ordering.entity.DishList;
import com.wansnow.ordering.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private DishListDao dishListDao;

    public Shop login(String shopId, String pwd){
        return shopDao.findShopByIdAndPwd(shopId, pwd);
    }

    public boolean registry(Shop shop){
        return shopDao.insertShop(shop)!=0;
    }

    public List<DishList> getAllDish(String shopId){
        return dishListDao.findDishListByShopId(shopId);
    }

    public boolean addDishList(DishList dishList){
        return dishListDao.insertDishList(dishList)!=0;
    }

    public boolean updateDishList(DishList dishList){
        return dishListDao.updateDishList(dishList)!=0;
    }

    public boolean deleteDishList(String dishId){
        return dishListDao.deleteDishList(dishId)!=0;
    }
}
