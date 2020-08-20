package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.DishListDaoImpl;
import com.wansnow.ordering.entity.DishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishListDao implements DishListDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<DishList> findDishListByShopId(String shopId) {
//        String sql = "SELECT * FROM dish_list WHERE shop_id='" + shopId + "'";
////        List<Object> queryList = new ArrayList<>();
////        List<DishList> dishLists = jdbcTemplate.query(sql,new RowMapper<DishList>() {
////            public DishList mapRow(ResultSet resultSet, int rowNum) throws SQLException {
////                DishList dish = new DishList();
////                dish.setDishId(resultSet.getString("dish_id"));
////                dish.setShopId(resultSet.getString("shop_id"));
////                dish.setDishName(resultSet.getString("dish_name"));
////                dish.setPrice(resultSet.getString("price"));
////                dish.setDishImage(resultSet.getString("dish_image"));
////                return dish;
////            }
////        },queryList.toArray());
        String sql = "select * from dish_list where shop_id ='" + shopId + "'";
        RowMapper<DishList> rowMapper = new BeanPropertyRowMapper<>(DishList.class);
        List<DishList> dishLists = jdbcTemplate.query(sql, rowMapper);//最后一个参数为id值
//        System.out.println(s);

        return dishLists;
    }

    @Override
    public DishList findDishListByDishId(String dishId) {
        String sql = "SELECT * FROM dish_list WHERE dish_id=?";
        DishList dishList = new DishList();
        jdbcTemplate.query(sql,new Object[]{dishId}, new RowCallbackHandler(){

            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                dishList.setDishId(resultSet.getString("dish_id"));
                dishList.setShopId(resultSet.getString("shop_id"));
                dishList.setDishName(resultSet.getString("dish_name"));
                dishList.setPrice(resultSet.getString("price"));
                dishList.setDishImage(resultSet.getString("dish_image"));
            }
        });
        return dishList;
    }

    @Override
    public int insertDishList(DishList dishList) {
        String sql = "INSERT INTO dish_list (dish_id,shop_id,dish_name,price,dish_image) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                dishList.getDishId(),
                dishList.getShopId(),
                dishList.getDishName(),
                dishList.getPrice(),
                dishList.getDishImage()
        );
    }

    @Override
    public int updateDishList(DishList dishList) {
        String sql = "UPDATE dish_list set dish_name = ?,price = ?,dish_image = ? WHERE dish_id = ?";
        return jdbcTemplate.update(sql,
                dishList.getDishName(),
                dishList.getPrice(),
                dishList.getDishImage(),
                dishList.getDishId()
        );
    }

    @Override
    public int deleteDishList(String dishId) {
        String sql = "DELETE FROM dish_list WHERE dish_id = ?";
        return jdbcTemplate.update(sql,dishId);
    }
}
