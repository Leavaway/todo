package com.todo.controller;

import com.sun.deploy.net.HttpResponse;
import com.todo.pojo.User;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//    @GetMapping("login")
//    public String login(HttpSession session){
//        if (session.getAttribute("user")!=null) {
//            return "admin/index";
//        } else {
//            return "redirect:/admin";
//        }
//    }
    @PostMapping("login")
    @ResponseBody
    public void login(HttpServletRequest httpServletRequest,
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
            user.setTasks(taskService.getTasks(user.getUsrId()));
//            return user.getTasks();
            HttpServletResponse httpServletResponse = new HttpServletResponse() {
                @Override
                public void addCookie(Cookie cookie) {

                }

                @Override
                public boolean containsHeader(String s) {
                    return false;
                }

                @Override
                public String encodeURL(String s) {
                    return null;
                }

                @Override
                public String encodeRedirectURL(String s) {
                    return null;
                }

                @Override
                public String encodeUrl(String s) {
                    return null;
                }

                @Override
                public String encodeRedirectUrl(String s) {
                    return null;
                }

                @Override
                public void sendError(int i, String s) throws IOException {

                }

                @Override
                public void sendError(int i) throws IOException {

                }

                @Override
                public void sendRedirect(String s) throws IOException {

                }

                @Override
                public void setDateHeader(String s, long l) {

                }

                @Override
                public void addDateHeader(String s, long l) {

                }

                @Override
                public void setHeader(String s, String s1) {

                }

                @Override
                public void addHeader(String s, String s1) {

                }

                @Override
                public void setIntHeader(String s, int i) {

                }

                @Override
                public void addIntHeader(String s, int i) {

                }

                @Override
                public void setStatus(int i) {

                }

                @Override
                public void setStatus(int i, String s) {

                }

                @Override
                public int getStatus() {
                    return 0;
                }

                @Override
                public String getHeader(String s) {
                    return null;
                }

                @Override
                public Collection<String> getHeaders(String s) {
                    return null;
                }

                @Override
                public Collection<String> getHeaderNames() {
                    return null;
                }

                @Override
                public String getCharacterEncoding() {
                    return null;
                }

                @Override
                public String getContentType() {
                    return null;
                }

                @Override
                public ServletOutputStream getOutputStream() throws IOException {
                    return null;
                }

                @Override
                public PrintWriter getWriter() throws IOException {
                    return null;
                }

                @Override
                public void setCharacterEncoding(String s) {

                }

                @Override
                public void setContentLength(int i) {

                }

                @Override
                public void setContentLengthLong(long l) {

                }

                @Override
                public void setContentType(String s) {

                }

                @Override
                public void setBufferSize(int i) {

                }

                @Override
                public int getBufferSize() {
                    return 0;
                }

                @Override
                public void flushBuffer() throws IOException {

                }

                @Override
                public void resetBuffer() {

                }

                @Override
                public boolean isCommitted() {
                    return false;
                }

                @Override
                public void reset() {

                }

                @Override
                public void setLocale(Locale locale) {

                }

                @Override
                public Locale getLocale() {
                    return null;
                }
            };
//            return "redirect:/";
        } else {
            attributes.addFlashAttribute("msg", "用户名或密码错误");
//            return "redirect:/admin";
//            return "todo";
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
