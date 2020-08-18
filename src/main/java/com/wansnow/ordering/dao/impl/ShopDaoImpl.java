package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.Shop;

public interface ShopDaoImpl {
    Shop findShopByIdAndPwd(String shopId, String pwd);
    int insertShop(Shop shop);
    int updateShop(Shop shop);
}
