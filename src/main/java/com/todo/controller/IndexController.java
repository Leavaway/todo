package com.todo.controller;

import com.todo.pojo.Task;
import com.todo.pojo.User;
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

@Controller
public class IndexController {
    @GetMapping()
    public String indexPage(HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        if (user!=null){
            httpServletRequest.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/html;charset=utf-8");
            StringBuilder stringBuilder = new StringBuilder();
            for (Task t:
                 user.getTasks()) {
                stringBuilder.append(t.getTask());
            }
            Cookie cookie = new Cookie("Tasks", URLEncoder.encode(stringBuilder.toString(), "utf-8"));
            httpServletResponse.addCookie(cookie);
            return "todo";
        }else{
            return "todo";
        }
    }
//    @GetMapping()
//    public String loadTasks(@RequestParam(value = "UsrId") String UsrId, HttpSession httpSession){
//        return "404";
//    }
}
