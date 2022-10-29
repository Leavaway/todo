package com.todo.dao;

import com.todo.pojo.Alert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface AlertDao {
    void addAlert(@Param("TaskId") int TaskId, @Param("UsrId") int UsrId, @Param("Alert") Timestamp Alert, @Param("Task") String Task,@Param("UsrEmail") String UsrEmail);
    List<Alert> getAlerts();
    void delAlert(@Param("TaskId") int TaskId, @Param("UsrId") int UsrId);
}
