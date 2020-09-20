package com.wansnow.ordering.service;

import com.wansnow.ordering.dao.UserDao;
import com.wansnow.ordering.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Cacheable(cacheNames = "allUsers", key = "\"all_users\"")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @CacheEvict(cacheNames = "userByEmail", key = "#user.email")
    public boolean updateUserInfo(User user){
        return userDao.updateUserInfo(user)!=0;
    }

    @CacheEvict(cacheNames = "userByEmail", key = "#user.email")
    public boolean updateUserPwd(String email, String newPwd){
        return userDao.updateUserPwd(email, newPwd)!=0;
    }
}
