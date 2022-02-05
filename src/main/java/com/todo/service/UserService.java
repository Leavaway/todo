package com.todo.service;
import com.todo.pojo.User;

import java.text.ParseException;

public interface UserService {

    public User checkUser(String username, String password);

    public boolean userRegister(String username, String password, String email);

    public User checkUserName(String username);

    public User checkUsrEmail(String useremail);

    public int getDate(int UsrId) throws ParseException;

    public String getUserName(int UsrId);

    public User getUser(int UsrId);
}
