package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.OrderingList;

public interface OrderingListDaoImpl {
    OrderingList findOrderingListById(String id);
    OrderingList findOrderingListByShopId(String shopId);
    OrderingList findOrderingListByEmail(String email);
    boolean insertOrdering(OrderingList list);
    boolean updateOrdering(OrderingList list);
}
