package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.ShopDao;
import com.wansnow.ordering.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl {
    @Autowired
    private ShopDao shopDao;

    public Shop login(String shopId, String pwd){
        return shopDao.findShopByIdAndPwd(shopId, pwd);
    }

    public boolean registry(Shop shop){
        return shopDao.insertShop(shop)!=0;
    }
}
