package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.UserDaoImpl;
import com.wansnow.ordering.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao implements UserDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByEmailAndPassword(String email, String pwd) {
        final User user = new User();
        String sql = "SELECT * FROM user WHERE email=? AND pwd=?";
        jdbcTemplate.query(sql, new Object[]{email, pwd}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setEmail(resultSet.getString("email"));
                user.setPwd(resultSet.getString("pwd"));
                user.setUsername(resultSet.getString("username"));
                user.setRealName(resultSet.getString("real_name"));
                user.setTel(resultSet.getString("tel"));
            }
        });
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE email=?";
        jdbcTemplate.query(sql, new Object[]{email}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setEmail(resultSet.getString("email"));
                user.setPwd(resultSet.getString("pwd"));
                user.setUsername(resultSet.getString("username"));
                user.setRealName(resultSet.getString("real_name"));
                user.setTel(resultSet.getString("tel"));
            }
        });
        return user;
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO user (email,pwd,username,real_name,tel) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                user.getEmail(),
                user.getPwd(),
                user.getUsername(),
                user.getRealName(),
                user.getTel()
        );
    }

    @Override
    public int updateUser(User user) {
        String sql = "UPDATE user set pwd = ?,username = ?,real_name = ?,tel = ? WHERE email = ?";
        return jdbcTemplate.update(sql,
                user.getPwd(),
                user.getUsername(),
                user.getRealName(),
                user.getTel(),
                user.getEmail()
        );
    }
}
