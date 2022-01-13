package com.todo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.pojo.TaskContent;
import com.todo.service.TaskService;
import com.todo.util.JavaToJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDao taskDao;
    @Override
    public List<Task> getTasks(int UserId) {
        return taskDao.getTask(UserId);
    }

    @Override
    public void newTask(int UserId, String string) {
        TaskContent taskContent = new TaskContent();
        taskContent.setContent(string);
        taskContent.setCreateTime(new Date());
        Task task = new Task();
        List<Task> task1 = taskDao.getTask(UserId);
        if(task1.size()==0){
            task.setTaskId(1);
            task.setUsrId(UserId);
            JSONObject jsonObject = JavaToJSON.javaToJSON(taskContent);
            task.setTask(jsonObject.toJSONString());
            taskDao.addNewTask(1, UserId, 0,0,0,null,jsonObject.toJSONString());
        }else {
            task.setTaskId(taskDao.getTaskId(UserId)+1);
            task.setUsrId(UserId);
            JSONObject jsonObject = JavaToJSON.javaToJSON(taskContent);
            task.setTask(jsonObject.toJSONString());
            taskDao.addNewTask(taskDao.getTaskId(UserId)+1, UserId, 0,0,0,null,jsonObject.toJSONString());
        }
    }

    @Override
    public int getTaskId(int UserId) {
        return taskDao.getTaskId(UserId);
    }

    @Override
    public void IsImportant(int UserId, int TaskId, int IsImportant) {
        if (IsImportant==0){
            taskDao.isImportantTask(UserId,TaskId,1);
        }else {
            taskDao.isImportantTask(UserId,TaskId,0);
        }
    }
}
