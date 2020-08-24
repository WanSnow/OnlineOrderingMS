package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.OrderingListDao;
import com.wansnow.ordering.entity.OrderingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderingServiceImpl {
    @Autowired
    private OrderingListDao orderingListDao;

    public OrderingList getOrderingListById(String orderingId){
        return orderingListDao.findOrderingListById(orderingId);
    }

    public List<OrderingList> getAllOrderingListByEmail(String email){
        return orderingListDao.findOrderingListByEmail(email);
    }

    public List<OrderingList> getAllOrderingListByShopId(String shopId){
        return orderingListDao.findOrderingListByShopId(shopId);
    }

    public boolean addOrderingList(OrderingList orderingList){
        return orderingListDao.insertOrdering(orderingList)!=0;
    }

    public boolean deleteOrderingList(String orderingId){
        return orderingListDao.deleteOrdering(orderingId)!=0;
    }
}
