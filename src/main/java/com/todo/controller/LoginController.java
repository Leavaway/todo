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
        User user1 = userService.checkUserName(username);
        User user = userService.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            //验证成功登录，设置session的attr后再次获取sessionId
            user.setTasks(taskService.getTasks(user.getUsrId()));
            return "redirect:/";
        } else {
            httpServletResponse.addIntHeader("loginStatus",404);
            return "login";
        }

    }
    @GetMapping("register")
    public String register(){
        return "register";
    }
    @PostMapping("register")
    public String UsrRegister(HttpServletRequest httpServletRequest){
        String UsrName = httpServletRequest.getParameter("UsrName");
        String UsrPwd = httpServletRequest.getParameter("UsrPwd");
        String UsrEmail = httpServletRequest.getParameter("UsrEmail");
        if(userService.userRegister(UsrName, UsrPwd, UsrEmail)){
            return "login";
        }else {
            return "404";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
