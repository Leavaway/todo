package com.todo.service.impl;

import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;
    @Override
    public List<Task> getTasks(int UserId) {
        return taskDao.getTask(UserId);
    }
}
