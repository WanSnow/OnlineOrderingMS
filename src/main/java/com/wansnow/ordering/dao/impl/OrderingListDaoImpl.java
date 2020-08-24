package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.OrderingList;

import java.util.List;

public interface OrderingListDaoImpl {
    OrderingList findOrderingListById(String orderingId);
    List<OrderingList> findOrderingListByShopId(String shopId);
    List<OrderingList> findOrderingListByEmail(String email);
    int insertOrdering(OrderingList list);
    int deleteOrdering(String  orderingId);
}
