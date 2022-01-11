package com.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class IndexController {
    @RequestMapping()
    public String indexPage(HttpSession httpSession){
        return "todo";
    }
}
