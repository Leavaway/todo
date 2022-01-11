package com.todo.dao;

import com.todo.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Mapper
@Repository
public interface TaskDao {
    List<Task> getTask(@Param("UsrId") int UsrId);
}
