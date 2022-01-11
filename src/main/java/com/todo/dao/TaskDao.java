package com.todo.dao;

import com.todo.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskDao {
    Task getTask(@Param("UsrId") int UsrId);
}
