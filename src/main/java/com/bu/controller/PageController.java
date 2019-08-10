package com.bu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }

    @RequestMapping("/getjoblist")
    public String getjoblist(){
        return "getjoblist";
    }

    @RequestMapping("/newjob")
    public String getnewjob(){
        return "newjob";
    }
}
