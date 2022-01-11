package com.todo.service.impl;

import com.todo.dao.UserDao;
import com.todo.pojo.User;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.checkLogin(username, password);
        return user;
    }

    @Override
    public boolean userRegister(String username, String password, String email) {
        User user = userDao.checkUserName(username);
        if (user!=null){
            return false;
        }
        int newId = (int)((Math.random()*9+1)*100000);
        User user1 = userDao.checkUsrId(newId);
        while (user1!=null){
            newId = (int)((Math.random()*9+1)*100000);
            user1 = userDao.checkUsrId(newId);
        }
        userDao.registerUser(newId, username, password, email);
        return true;
    }

    @Override
    public User checkUserName(String username) {
        User user = userDao.checkUserName(username);
        return user;
    }
}
