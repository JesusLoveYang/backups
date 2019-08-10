package com.bu.controller;

import com.bu.entity.MyJob;
import com.bu.entity.User;
import com.bu.service.JobService;
import com.bu.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;

    /*
     * 用户的登录
     */
    // 定义子路由，由于提交的是表单数据，因此采用post请求. 另外，采用responsebody，返回json格式的字符串
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private Map<String, Object> login(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 将用户名放到session中
        request.getSession().setAttribute("username", username);

        // 具体逻辑
        // 1、判空
        if (username == null || password == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码不能为空");
            return modelMap;
        }

        // 2、获得了用户名和密码，将其作为参数，获得token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 3、调用subject 的login进行登录
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            modelMap.put("success", true);
            return modelMap;
        } catch (AuthenticationException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码错误");
            return modelMap;
        }

    }

    /*
    当用户点击退出按钮的时候 调用subject的logout方法
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private Map<String, Object> logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", true);
        return modelMap;
    }

    /*
     获取任务列表
     */
    @RequestMapping(value = "/getjoblist", method = RequestMethod.GET)
    private Map<String, Object> getJobList(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 从session会话中 获取用户对象
        // String username = "yang";
        String username = (String) request.getSession().getAttribute("username");

        try {
            User user = new User();
            user.setUserName(username);
            List<MyJob> jobList = jobService.getJobList(user.getUserName(), 0, 100);
            modelMap.put("user", user);
            modelMap.put("jobList", jobList);
            // 列出店铺成功后，将店铺放到session中，用于权限的
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }

        return modelMap;
    }

    /*
    新增任务
     */
    @RequestMapping(value = "/insertjob", method = RequestMethod.POST)
    private Map<String, Object> insertjob(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String jobname = request.getParameter("jobname");
        String jobgroup = request.getParameter("jobgroup");
        String triggername = request.getParameter("triggername");
        String triggergroup = request.getParameter("triggergroup");
        String cron = request.getParameter("cron");

        if ("".equals(jobname) || "".equals(jobgroup) || "".equals(triggername) || "".equals(triggergroup) || "".equals(cron)) {
            modelMap.put("success", false);
            return modelMap;
        }

        MyJob myJob = new MyJob();

        // 从session中获取用户
        String username = (String) request.getSession().getAttribute("username");

        myJob.setUserId(userService.queryUser(username).getUserId());
        myJob.setJobName(jobname);
        myJob.setJobGroup(jobgroup);
        myJob.setTriggerName(triggername);
        myJob.setTriggerGroup(triggergroup);
        myJob.setCronExpression(cron);

        int effectNum = 0;
        try {
            effectNum = jobService.insertJob(myJob);
            if (effectNum > 0) {
                modelMap.put("success", true);
                return modelMap;
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "添加任务失败");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "添加任务失败");
            return modelMap;
        }

    }

}
