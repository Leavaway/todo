package com.todo.dao;

import com.alibaba.fastjson.JSONObject;
import com.todo.pojo.DailyTask;
import com.todo.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface TaskDao {
    List<Task> getTask(@Param("UsrId") int UsrId);
    String getTarTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    Task getSpeTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    int getTaskId(@Param("UsrId") int UsrId);
    void addNewTask(@Param("TaskId") int TaskId, @Param("UsrId") int UsrId, @Param("IsImportant") int IsImportant,
                    @Param("IsComplete") int IsComplete, @Param("IsDaily") int IsDaily,
                    @Param("AlertTime") Date AlertTime, @Param("Task") String Task);
    void updateTask(@Param("UsrId") int UsrId, @Param("Task") String Task);
    void isImportantTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId,@Param("IsImportant") int IsImportant);
    String getEmail(@Param("UsrId") int UsrId);
    void setAlert(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId,@Param("AlertTime")
            Timestamp AlertTime);
    List<Task> getDailyTask(@Param("UsrId") int UsrId);
    void deleteTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    void completeTask(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    void addDailyTask(@Param("TaskId") int TaskId, @Param("UsrId") int UsrId, @Param("IsImportant") int IsImportant,
                      @Param("IsComplete") int IsComplete, @Param("IsDaily") int IsDaily, @Param("Task") String Task);
    void delDailyTask(@Param("UsrId") int UsrId,@Param("Task") String Task);
    void setDaily(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    void cancelDaily(@Param("UsrId") int UsrId,@Param("TaskId") int TaskId);
    List<DailyTask> getAllDaily();
}
