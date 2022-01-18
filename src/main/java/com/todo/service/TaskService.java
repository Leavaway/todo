package com.todo.service;

import com.todo.pojo.Task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> getTasks(int UserId);
    void newTask(int UserId, String string);
    int getTaskId(int UserId);
    void IsImportant(int UserId, int TaskId, int IsImportant);
    void setAlert(int UserId, int TaskId,String date) throws ParseException;
    void delTask(int UserId, int TaskId);
    void comTask(int UserId, int TaskId);
    void checkAlert() throws ParseException;
}
