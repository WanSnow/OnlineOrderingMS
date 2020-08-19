package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.OrderingListDaoImpl;
import com.wansnow.ordering.entity.OrderingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderingListDao implements OrderingListDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OrderingList findOrderingListById(String id) {
        return null;
    }

    @Override
    public OrderingList findOrderingListByShopId(String shopId) {
        return null;
    }

    @Override
    public OrderingList findOrderingListByEmail(String email) {
        return null;
    }

    @Override
    public boolean insertOrdering(OrderingList list) {
        return false;
    }

    @Override
    public boolean updateOrdering(OrderingList list) {
        return false;
    }
}
