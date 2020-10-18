package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.OrderingList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderingListDaoImpl {
    @Select(value = "SELECT * FROM order_info WHERE ordering_id=#{orderingId}")
    OrderingList findOrderingListById(String orderingId);
    List<OrderingList> findOrderingListByShopId(String shopId);
    List<OrderingList> findOrderingListByEmail(String email);
    @Insert(value = "INSERT INTO order_info (ordering_id,shop_id,shop_name,email,real_name,tel,appointment_time,order_list,is_ordering) VALUES (#{list.orderingId},#{list.shopId},#{list.shopName},#{list.email},#{list.realName},#{list.tel},#{list.appointmentTime},#{list.orderList},false)")
    int insertOrdering(OrderingList list);
    int deleteOrdering(String  orderingId);
    @Update(value = "UPDATE order_info SET is_verify=true WHERE ordering_id=#{orderingId}")
    int confirmOrdering(String orderingId);
}
