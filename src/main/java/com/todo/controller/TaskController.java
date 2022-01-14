package com.todo.controller;

import com.todo.pojo.User;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping()
    public String addNewTask(HttpServletRequest httpServletRequest, HttpSession httpSession,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        String taskContent = httpServletRequest.getParameter("taskContent");
        String isImportant =httpServletRequest.getParameter("isImportant");
        String TaskId =httpServletRequest.getParameter("isImportant");
        if (taskContent!=null){
            User user = (User) httpSession.getAttribute("user");
            int UsrId = user.getUsrId();
            taskService.newTask(UsrId, taskContent);
            //在页面更改信息后需要重新设置对应的session,请注意
            user.setTasks(taskService.getTasks(UsrId));
            httpSession.setAttribute("user",user);
            return "redirect:/";
        }else if (isImportant!=null&&TaskId!=null){
            User user = (User) httpSession.getAttribute("user");
            taskService.IsImportant(user.getUsrId(),Integer.parseInt(TaskId),Integer.parseInt(isImportant));
            return "redirect:/";
        }
//        Cookie cookie = new Cookie("TaskId", URLEncoder.encode(TaskId, "utf-8"));
//        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }
}
