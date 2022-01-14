package com.todo.service.impl;

import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Task task = new Task();
        List<Task> task1 = taskDao.getTask(UserId);
        if(task1.size()==0){
            task.setTaskId(1);
            task.setUsrId(UserId);
            task.setTask(string);
            taskDao.addNewTask(1, UserId, 0,0,0,null,string);
        }else {
            task.setTaskId(taskDao.getTaskId(UserId)+1);
            task.setUsrId(UserId);
            task.setTask(string);
            taskDao.addNewTask(taskDao.getTaskId(UserId)+1, UserId, 0,0,0,null,string);
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
