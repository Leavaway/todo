package com.todo.dao;

import com.todo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User checkLogin( String UsrName, @Param("UsrPwd") String UsrPwd);
    User checkUserName(@Param("UsrName") String UsrName);
    User checkUsrEmail(@Param("UsrEmail") String UsrEmail);
    void registerUser(@Param("UsrId") int UsrId, @Param("UsrName") String UsrName, @Param("UsrPwd") String UsrPwd, @Param("UsrEmail") String UsrEmail);
    User checkUsrId(@Param("UsrId") int UsrId);
}
