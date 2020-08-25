package com.wansnow.ordering.dao.impl;

import com.wansnow.ordering.entity.User;

public interface UserDaoImpl {
    User findUserByEmailAndPassword(String email, String pwd);
    User findUserByEmail(String email);
    int insertUser(User user);
    int updateUserInfo(User user);
    int updateUserPwd(String email, String pwd);
}
