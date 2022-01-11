package com.todo.controller;

import com.todo.pojo.User;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

//    @GetMapping("login")
//    public String login(HttpSession session){
//        if (session.getAttribute("user")!=null) {
//            return "admin/index";
//        } else {
//            return "redirect:/admin";
//        }
//    }
    @PostMapping("login")
    public String login(HttpServletRequest httpServletRequest,
                        HttpSession session,
                        RedirectAttributes attributes) {
        String username = httpServletRequest.getParameter("UsrName");
        String password = httpServletRequest.getParameter("UsrPwd");
        //访问时的sessionId
        System.out.println(session.getId());
        //验证用户名和密码
        User user1 = userService.checkUserName(username);
//        if(user1==null){
//            return "404";
//        }
        User user = userService.checkUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            //验证成功登录，设置session的attr后再次获取sessionId
            System.out.println(session.getAttribute("user"));
            user.setTasks(taskService.getTasks(1));
            System.out.println(user.getTasks());
            return "todo";
        } else {
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
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
