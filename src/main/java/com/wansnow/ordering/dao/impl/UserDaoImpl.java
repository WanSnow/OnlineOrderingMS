package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.User;

import java.util.List;

public interface UserDaoImpl {
    User findUserByEmailAndPassword(String email, String pwd);
    User findUserByEmail(String email);
    List<User> getAllUsers();
    int insertUser(User user);
    int updateUserInfo(User user);
    int updateUserPwd(String email, String pwd);
}
