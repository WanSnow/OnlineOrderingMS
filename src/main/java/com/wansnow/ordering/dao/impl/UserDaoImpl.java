package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDaoImpl {
    @Select(value = "SELECT * FROM user_info WHERE email=#{email} AND pwd=#{pwd}")
    User findUserByEmailAndPassword(String email, String pwd);
    @Select(value = "SELECT * FROM user_info WHERE email=#{email}")
    User findUserByEmail(String email);
    List<User> getAllUsers();
    @Insert(value = "INSERT INTO user_info (email,pwd,username,real_name,tel) VALUES (#{user.email},#{user.pwd},#{user.username},#{user.realName},#{tel})")
    int insertUser(User user);
    @Update(value = "UPDATE user_info SET username=#{user.username},real_name=#{user.realName},tel=#{user.tel} WHERE email=#{user.email}")
    int updateUserInfo(User user);
    @Update(value = "UPDATE user_info SET pwd=#{user.pwd} WHERE email=#{user.email}")
    int updateUserPwd(String email, String pwd);
}
