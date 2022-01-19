package com.todo.controller;

import com.todo.pojo.Task;
import com.todo.pojo.User;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public String loginPage(HttpSession session){
        if (session.getAttribute("user")!=null) {
            return "login";
        }
//        return "admin/login";
        return "login";
    }
//    @RequestMapping()
//    public void loginReturn(@RequestParam(value = "task*") String task){
//
//    }

    @PostMapping("login")
    public String login(HttpServletRequest httpServletRequest,
                        HttpSession session,
                        HttpServletResponse httpServletResponse) throws IOException {
        String username = httpServletRequest.getParameter("UsrName");
        String password = httpServletRequest.getParameter("UsrPwd");
        //验证用户名和密码
        User user = userService.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            //验证成功登录，设置session的attr后再次获取sessionId
            user.setTasks(taskService.getTasks(user.getUsrId()));
            return "redirect:/";
        } else {
            Cookie cookie = new Cookie("status","0");
            httpServletResponse.addCookie(cookie);
            return "login";
        }

    }
    @GetMapping("register")
    public String register(){
        return "register";
    }
    @PostMapping("register")
    public String UsrRegister(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        String UsrName = httpServletRequest.getParameter("UsrName");
        String UsrPwd = httpServletRequest.getParameter("UsrPwd");
        String UsrEmail = httpServletRequest.getParameter("UsrEmail");
        String CheckPwd = httpServletRequest.getParameter("CheckPwd");
        User user1 = userService.checkUserName(UsrName);
        User user2 = userService.checkUsrEmail(UsrEmail);
        if(!UsrPwd.equals(CheckPwd)){
            Cookie cookie = new Cookie("status","7");
            httpServletResponse.addCookie(cookie);
            return "register";
        }
        if (user1!=null){
            Cookie cookie = new Cookie("status","5");
            httpServletResponse.addCookie(cookie);
            return "register";
        }else if (user2!=null){
            Cookie cookie = new Cookie("status","6");
            httpServletResponse.addCookie(cookie);
            return "register";
        }
        if(userService.userRegister(UsrName, UsrPwd, UsrEmail)){
            return "login";
        }else {
            Cookie cookie = new Cookie("status","5");
            httpServletResponse.addCookie(cookie);
            return "register";
        }
    }


}
