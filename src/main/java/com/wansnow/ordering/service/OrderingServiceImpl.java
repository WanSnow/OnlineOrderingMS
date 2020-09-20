package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.OrderingListDao;
import com.wansnow.ordering.entity.OrderingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderingServiceImpl {
    @Autowired
    private OrderingListDao orderingListDao;

    @Cacheable(cacheNames = "orderingListById", key = "#orderingId")
    public OrderingList getOrderingListById(String orderingId){
        return orderingListDao.findOrderingListById(orderingId);
    }

    @Cacheable(cacheNames = "orderingListsByEmail", key = "#email")
    public List<OrderingList> getAllOrderingListByEmail(String email){
        return orderingListDao.findOrderingListByEmail(email);
    }

    @Cacheable(cacheNames = "orderingListsByShopId", key = "#shopId")
    public List<OrderingList> getAllOrderingListByShopId(String shopId){
        return orderingListDao.findOrderingListByShopId(shopId);
    }

    @Cacheable(cacheNames = "orderingListById", key = "#orderingList.orderingId")
    public boolean addOrderingList(OrderingList orderingList){
        return orderingListDao.insertOrdering(orderingList)!=0;
    }

    @CacheEvict(cacheNames = "orderingListById", key = "#orderingId")
    public boolean confirmOrderingList(String orderingId){
        return orderingListDao.confirmOrdering(orderingId)!=0;
    }

    @CacheEvict(cacheNames = "orderingListById", key = "#orderingId")
    public boolean deleteOrderingList(String orderingId){
        return orderingListDao.deleteOrdering(orderingId)!=0;
    }
}
