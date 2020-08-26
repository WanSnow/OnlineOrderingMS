package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.OrderingListDaoImpl;
import com.wansnow.ordering.entity.OrderingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderingListDao implements OrderingListDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OrderingList findOrderingListById(String orderingId) {
        OrderingList orderingList = new OrderingList();
        String sql = "SELECT * FROM ordering_list WHERE ordering_id=?";
        jdbcTemplate.query(sql, new Object[]{orderingId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                orderingList.setOrderingId(resultSet.getString("ordering_id"));
                orderingList.setShopId(resultSet.getString("shop_id"));
                orderingList.setShopName(resultSet.getString("shop_name"));
                orderingList.setEmail(resultSet.getString("email"));
                orderingList.setRealName(resultSet.getString("real_name"));
                orderingList.setAppointmentTime(resultSet.getString("appointment_time"));
                orderingList.setTel(resultSet.getString("tel"));
                orderingList.setOrderList(resultSet.getString("order_list"));
                orderingList.setOrdering(resultSet.getBoolean("is_ordering"));
            }
        });
        return orderingList;
    }

    @Override
    public List<OrderingList> findOrderingListByShopId(String shopId) {
        String sql = "select * from ordering_list where shop_id = ?";
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(){
            public Object mapRow(ResultSet rs,int index) throws SQLException{
                OrderingList orderingList = new OrderingList();
                orderingList.setOrderingId(rs.getString("ordering_id"));
                orderingList.setShopId(rs.getString("shop_id"));
                orderingList.setShopName(rs.getString("shop_name"));
                orderingList.setEmail(rs.getString("email"));
                orderingList.setRealName(rs.getString("real_name"));
                orderingList.setAppointmentTime(rs.getString("appointment_time"));
                orderingList.setTel(rs.getString("tel"));
                orderingList.setOrderList(rs.getString("order_list"));
                orderingList.setOrdering(rs.getBoolean("is_ordering"));
                return orderingList;
            }
        };
        List<OrderingList> orderingLists = jdbcTemplate.query(sql, rowMapper,shopId);
        return orderingLists;
    }

    @Override
    public List<OrderingList> findOrderingListByEmail(String email) {
        String sql = "select * from ordering_list where email = ?";
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(){
            public Object mapRow(ResultSet rs,int index) throws SQLException{
                OrderingList orderingList = new OrderingList();
                orderingList.setOrderingId(rs.getString("ordering_id"));
                orderingList.setShopId(rs.getString("shop_id"));
                orderingList.setShopName(rs.getString("shop_name"));
                orderingList.setEmail(rs.getString("email"));
                orderingList.setRealName(rs.getString("real_name"));
                orderingList.setAppointmentTime(rs.getString("appointment_time"));
                orderingList.setTel(rs.getString("tel"));
                orderingList.setOrderList(rs.getString("order_list"));
                orderingList.setOrdering(rs.getBoolean("is_ordering"));
                return orderingList;
            }
        };
        List<OrderingList> orderingLists = jdbcTemplate.query(sql, rowMapper,email);
        return orderingLists;
    }

    @Override
    public int insertOrdering(OrderingList list) {
        String sql = "INSERT INTO ordering_list (ordering_id,shop_id,shop_name,email,real_name,appointment_time,tel,order_list,is_ordering) VALUES (?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                list.getOrderingId(),
                list.getShopId(),
                list.getShopName(),
                list.getEmail(),
                list.getRealName(),
                list.getAppointmentTime(),
                list.getTel(),
                list.getOrderList(),
                list.isOrdering()
        );
    }

    @Override
    public int deleteOrdering(String orderingId) {
        String sql = "DELETE FROM ordering_list WHERE ordering_id = ?";
        return jdbcTemplate.update(sql,orderingId);
    }

    @Override
    public int confirmOrdering(String orderingId) {
        String sql = "update ordering_list set is_ordering = true where ordering_id = ?";
        return jdbcTemplate.update(sql,orderingId);
    }


}
