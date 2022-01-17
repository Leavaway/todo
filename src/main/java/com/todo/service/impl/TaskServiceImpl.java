package com.todo.service.impl;

import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.service.TaskService;
import com.todo.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        taskDao.isImportantTask(UserId,TaskId,IsImportant);
    }

    @Override
    public void setAlert(int UserId, int TaskId, String date) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = simpleDateFormat.parse(date);
        Timestamp sqlDate = new Timestamp(date1.getTime());
        taskDao.setAlert(UserId, TaskId, sqlDate);
        EmailService emailService = new EmailService();
        String user = "15757610036@163.com";
        String password = "TLTWNSKXWDZWBFSJ";
        String host = "smtp.163.com";
        String from = "15757610036@163.com";
        String to = taskDao.getEmail(UserId);// 收件人
        String subject = "AlertEmail";
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>欢迎使用wedo，您设置的提醒时间为：<br/><h2 style='color:green'>"+date1+"</h2><br/>本邮件由系统自动发出，请勿回复。<br/>请注意您的安排，感谢您的使用。<br/>Wedo 运营组</div>"
                +"</div>");
        try {
            String res = emailService.sendMail(user, password, host, from, to,
                    subject, sb.toString(),date1);
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void delTask(int UserId, int TaskId) {
        taskDao.deleteTask(UserId,TaskId);
    }

    @Override
    public void comTask(int UserId, int TaskId) {
        taskDao.completeTask(UserId,TaskId);
    }
}
