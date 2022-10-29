package com.todo.service.impl;

import com.todo.dao.UserDao;
import com.todo.pojo.User;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        userDao.registerUser(newId, username, password, email,date1);
        return true;
    }

    @Override
    public User checkUserName(String username) {
        User user = userDao.checkUserName(username);
        return user;
    }

    @Override
    public User checkUsrEmail(String useremail) {
        return userDao.checkUsrEmail(useremail);
    }

    @Override
    public int getDate(int UsrId) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        String date = simpleDateFormat.format(date1);
        Date date2 = simpleDateFormat.parse(date);
        java.sql.Date sqlDate = userDao.getRegisterDate(UsrId);
        Date date3 = new Date(sqlDate.getTime());
        int day= (int) ((date2.getTime()-date3.getTime())/(24*60*60*1000));
        return day;
    }


}
