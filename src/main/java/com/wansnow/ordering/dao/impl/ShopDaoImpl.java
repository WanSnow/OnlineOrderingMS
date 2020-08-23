package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.Shop;

import java.util.List;

public interface ShopDaoImpl {
    Shop findShopByIdAndPwd(String shopId, String pwd);
    Shop findShopById(String shopId);
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    List<Shop> getAllShops();
}
