package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShopDaoImpl {

    @Select(value = "SELECT * FROM shop_info WHERE shop_id=#{shopId} AND pwd=#{pwd}")
    Shop findShopByIdAndPwd(String shopId, String pwd);
    @Select(value = "SELECT * FROM shop_info WHERE shop_id=#{shopId}")
    Shop findShopById(String shopId);
    @Insert(value = "INSERT INTO shop_info (shop_id,pwd,shop_name,owner_name,tel,addr,id_verify) VALUES (#{shop.shopId},#{shop.pwd},#{shop.shopName},#{shop.ownerName},#{shop.tel},#{shop.addr},false)")
    int insertShop(Shop shop);
    @Update(value = "UPDATE shop_info SET shop_name=#{shop.shopName},owner_name=#{shop.ownerName},tel=#{shop.tel},addr=#{shop.addr},is_verify=#{shop.isVerify} WHERE shop_id=#{shop.shopId}")
    int updateShop(Shop shop);
    List<Shop> getAllShops();
    @Update(value = "UPDATE shop_info SET pwd=#{newPwd} WHERE shop_id=#{shopId}")
    int updateShopPwd(String shopId, String newPwd);
    @Update(value = "UPDATE shop_info SET is_verify=true WHERE shop_id=#{shopId}")
    int confirmShop(String shopId);
}
