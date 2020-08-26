package com.wansnow.ordering.dao;

import com.wansnow.ordering.dao.impl.UserDaoImpl;
import com.wansnow.ordering.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public List<User> getAllUsers() {
        String sql = "select * from user";
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(){
            public Object mapRow(ResultSet rs,int index) throws SQLException{
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setPwd(rs.getString("pwd"));
                user.setUsername(rs.getString("username"));
                user.setRealName(rs.getString("real_name"));
                user.setTel(rs.getString("tel"));
                return user;
            }
        };
        List<User> users = jdbcTemplate.query(sql, rowMapper);

        return users;
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
    public int updateUserInfo(User user) {
        String sql = "UPDATE user set username = ?,real_name = ?,tel = ? WHERE email = ?";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getRealName(),
                user.getTel(),
                user.getEmail()
        );
    }

    @Override
    public int updateUserPwd(String email, String pwd) {
        String sql = "UPDATE user set pwd = ? WHERE email = ?";
        return jdbcTemplate.update(sql,
                pwd,
                email
        );
    }
}
