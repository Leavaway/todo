package com.todo.controller;

import com.todo.pojo.User;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping()
    public void addNewTask(HttpServletRequest httpServletRequest, HttpSession httpSession){
        System.out.println("add new task");
        String taskContent = httpServletRequest.getParameter("taskContent");
        User user = (User) httpSession.getAttribute("user");
        int UsrId = user.getUsrId();
        System.out.println(UsrId);
        taskService.newTask(UsrId, taskContent);
    }
}
