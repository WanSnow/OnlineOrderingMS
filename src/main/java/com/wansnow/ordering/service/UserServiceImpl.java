package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.UserDao;
import com.wansnow.ordering.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    public User login(String email, String password) {
        return userDao.findUserByEmailAndPassword(email, password);
    }

    public boolean register(User user) {
        User userByEmail = userDao.findUserByEmail(user.getEmail());
        if (userByEmail!=null&&userByEmail.getEmail() != null && userByEmail.getEmail().equals(user.getEmail())) {
            return true;
        }
        return userDao.insertUser(user) != 0;
    }
}
