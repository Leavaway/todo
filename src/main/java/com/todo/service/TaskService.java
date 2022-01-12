package com.todo.service;

import com.todo.pojo.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks(int UserId);
    void newTask(int UserId, String string);
    int getTaskId(int UserId);
}
