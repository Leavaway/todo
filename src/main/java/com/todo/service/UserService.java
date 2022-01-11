package com.todo.service;
import com.todo.pojo.User;

public interface UserService {

    public User checkUser(String username, String password);

    public boolean userRegister(String username, String password, String email);

    public User checkUserName(String username);
}
