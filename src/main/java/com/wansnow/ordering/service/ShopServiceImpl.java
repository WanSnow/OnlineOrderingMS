package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.DishListDao;
import com.wansnow.ordering.dao.ShopDao;
import com.wansnow.ordering.entity.DishList;
import com.wansnow.ordering.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl {
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private DishListDao dishListDao;

    @Cacheable(cacheNames = "allShopList", key = "\"all_shop_list\"")
    public List<Shop> getAllShops(){
        return shopDao.getAllShops();
    }

    @Cacheable(cacheNames = "shopById", key = "#shopId")
    public Shop getShop(String shopId){
        return shopDao.findShopById(shopId);
    }

    public Shop login(String shopId, String pwd){
        return shopDao.findShopByIdAndPwd(shopId, pwd);
    }

    public boolean registry(Shop shop){
        return shopDao.insertShop(shop)!=0;
    }

    @Cacheable(cacheNames = "dishById", key = "#dishId")
    public DishList getDish(String dishId){return dishListDao.findDishListByDishId(dishId);}

    @Cacheable(cacheNames = "allDishByShopId", key = "shopId")
    public List<DishList> getAllDish(String shopId){
        return dishListDao.findDishListByShopId(shopId);
    }

    @Cacheable(cacheNames = "dishById", key = "#dishList.dishId")
    public boolean addDishList(DishList dishList){
        return dishListDao.insertDishList(dishList)!=0;
    }

    @CacheEvict(cacheNames = "dishById", key = "#dishList.dishId")
    public boolean updateDishList(DishList dishList){
        return dishListDao.updateDishList(dishList)!=0;
    }

    @CacheEvict(cacheNames = "dishById", key = "#dishList.dishId")
    public boolean deleteDishList(String dishId){
        return dishListDao.deleteDishList(dishId)!=0;
    }

    @CacheEvict(cacheNames = "shopById", key = "#shopId")
    public boolean updateShopPwd(String shopId, String pwd){
        return shopDao.updateShopPwd(shopId,pwd)!=0;
    }

    @CacheEvict(cacheNames = "shopById", key = "#shop.shopId")
    public boolean updateShopInfo(Shop shop){
        return shopDao.updateShop(shop)!=0;
    }

    @CacheEvict(cacheNames = "shopById", key = "#shop.shopId")
    public boolean confirmShop(String shopId){
        return shopDao.confirmShop(shopId)!=0;
    }
}
