package com.todo.service.impl;

import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;
    @Override
    public Task getTasks(int UserId) {
        Task task = taskDao.getTask(UserId);
        return task;
    }
}
