package com.todo.controller;

import com.alibaba.fastjson.JSONObject;
import com.todo.dao.TaskDao;
import com.todo.pojo.Task;
import com.todo.pojo.User;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskDao taskDao;
    @GetMapping()
    public String indexPage(HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        if (user!=null){
            httpServletRequest.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/html;charset=utf-8");
            StringBuilder stringBuilder = new StringBuilder();
            for (Task t:
                 user.getTasks()) {
                String jsonString = JSONObject.toJSONString(t);
                stringBuilder.append(jsonString);
                stringBuilder.append("&");
            }
            Cookie cookie = new Cookie("Tasks", URLEncoder.encode(stringBuilder.toString(), "utf-8"));
            Cookie cookie1 = new Cookie("login", "1");
            httpServletResponse.addCookie(cookie);
            httpServletResponse.addCookie(cookie1);
            return "todo";
        }else{
            return "todo";
        }
    }
    @GetMapping("help")
    public String getHelp(){
        return "help";
    }
    @GetMapping("newTask")
    public void getNewTaskId(HttpSession httpSession, HttpServletResponse httpServletResponse){
        User user = (User) httpSession.getAttribute("user");
        List<Task> task1 = taskDao.getTask(user.getUsrId());
        if(task1.size()==0){
            httpServletResponse.addIntHeader("TaskId",1);
            httpServletResponse.addIntHeader("UsrId",user.getUsrId());
        }else {
            httpServletResponse.addIntHeader("TaskId",taskService.getTaskId(user.getUsrId())+1);
            httpServletResponse.addIntHeader("UsrId",user.getUsrId());
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,
                         HttpSession session,
                         HttpServletResponse httpServletResponse){
        session.invalidate();
        Cookie cookie = new Cookie("Task",null);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }
}
