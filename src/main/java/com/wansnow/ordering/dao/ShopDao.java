package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.ShopDaoImpl;
import com.wansnow.ordering.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ShopDao implements ShopDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Shop findShopByIdAndPwd(String shopId, String pwd) {
        Shop shop = new Shop();
        String sql = "SELECT * FROM shop WHERE shop_id=? AND pwd=?";
        jdbcTemplate.query(sql, new Object[]{shopId, pwd}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                shop.setShopId(resultSet.getString("shop_id"));
                shop.setPwd(resultSet.getString("pwd"));
                shop.setShopName(resultSet.getString("shop_name"));
                shop.setOwnerName(resultSet.getString("owner_name"));
                shop.setTel(resultSet.getString("tel"));
                shop.setAddr(resultSet.getString("addr"));
                shop.setVerify(resultSet.getBoolean("is_verify"));
            }
        });
        return shop;
    }

    @Override
    public Shop findShopById(String shopId) {
        Shop shop = new Shop();
        String sql = "SELECT * FROM shop WHERE shop_id=?";
        jdbcTemplate.query(sql, new Object[]{shopId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                shop.setShopId(resultSet.getString("shop_id"));
                shop.setShopName(resultSet.getString("shop_name"));
                shop.setOwnerName(resultSet.getString("owner_name"));
                shop.setTel(resultSet.getString("tel"));
                shop.setAddr(resultSet.getString("addr"));
                shop.setVerify(resultSet.getBoolean("is_verify"));
            }
        });
        return shop;
    }

    @Override
    public int insertShop(Shop shop) {
        String sql = "INSERT INTO shop (shop_id,pwd,shop_name,owner_name,tel,addr,is_verify) VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                shop.getShopId(),
                shop.getPwd(),
                shop.getShopName(),
                shop.getOwnerName(),
                shop.getTel(),
                shop.getAddr(),
                shop.isVerify()
        );
    }

    @Override
    public int updateShop(Shop shop) {
        String sql = "UPDATE shop set shop_name = ?,owner_name = ?,tel = ?,addr = ? WHERE shop_id = ?";
        return jdbcTemplate.update(sql,
                shop.getShopName(),
                shop.getOwnerName(),
                shop.getTel(),
                shop.getAddr(),
                shop.getShopId()
        );
    }

    @Override
    public List<Shop> getAllShops() {
        String sql = "select * from shop";
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(){
            public Object mapRow(ResultSet rs,int index) throws SQLException{
                Shop shop = new Shop();
                shop.setShopId(rs.getString("shop_id"));
                shop.setShopName(rs.getString("shop_name"));
                shop.setOwnerName(rs.getString("owner_name"));
                shop.setTel(rs.getString("tel"));
                shop.setAddr(rs.getString("addr"));
                shop.setVerify(rs.getBoolean("is_verify"));
                return shop;
            }
        };
        List<Shop> shops = jdbcTemplate.query(sql, rowMapper);

        return shops;
    }

    @Override
    public int updateShopPwd(String shopId, String newPwd) {
        String sql = "UPDATE shop set pwd = ? WHERE shop_id = ?";
        return jdbcTemplate.update(sql,
                newPwd,
                shopId
        );
    }

    @Override
    public int confirmShop(String shopId) {
        String sql = "update shop set is_verify = true where shop_id = ?";
        return jdbcTemplate.update(sql,shopId);
    }
}
