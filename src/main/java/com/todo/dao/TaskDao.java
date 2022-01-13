package com.todo.dao;

import com.alibaba.fastjson.JSONObject;
import com.todo.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

@Mapper
@Repository
public interface TaskDao {
    List<Task> getTask(@Param("UsrId") int UsrId);
    int getTaskId(@Param("UsrId") int UsrId);
    void addNewTask(@Param("TaskId") int TaskId, @Param("UsrId") int UsrId, @Param("IsImportant") int IsImportant,
                    @Param("IsComplete") int IsComplete, @Param("IsDaily") int IsDaily,
                    @Param("AlertTime") Date AlertTime, @Param("Task") String Task);
    void updateTask(@Param("UsrId") int UsrId, @Param("Task") String Task);
    void isImportantTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId,@Param("IsImportant") int IsImportant);
}
