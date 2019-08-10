package com.bu.controller;

import com.bu.entity.MyJob;
import com.bu.job.SyncJob;
import com.bu.service.JobDispatchService;
import com.bu.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/job")
public class JobDispatchController {
    @Autowired
    private JobDispatchService jobDispatchService;
    @Autowired
    private JobService jobService;

    // 添加一个job
    @RequestMapping(value = "/addjob", method = RequestMethod.GET)
    public Map<String, Object> addjob(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // String tempJob = "job1";
        String tempJob = request.getParameter("jobname");

        try {
            MyJob job = jobService.getJob(tempJob);
            String str = jobDispatchService.addQuartzJob(job, SyncJob.class);
            modelMap.put("str", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg : ", e.toString());
        }
        return modelMap;
    }

    //暂停job
    @RequestMapping(value = "/pausejob", method = RequestMethod.GET)
    public Map<String, Object> pausejob(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String tempJob = request.getParameter("jobname");

        try {
            MyJob job = jobService.getJob(tempJob);
            jobDispatchService.pauseJob(job);
            modelMap.put("str", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg : ", e.toString());
        }
        return modelMap;
    }

    //恢复job
    @RequestMapping(value = "/resumejob", method = RequestMethod.GET)
    public Map<String, Object> resumeJob(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String tempJob = request.getParameter("jobname");

        try {
            MyJob job = jobService.getJob(tempJob);
            jobDispatchService.resumeJob(job);
            modelMap.put("str", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg : ", e.toString());
        }
        return modelMap;
    }

    //删除job
    @RequestMapping(value = "/deletejob", method = RequestMethod.GET)
    public Map<String, Object> deleteJob(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String tempJob = request.getParameter("jobname");

        try {
            MyJob job = jobService.getJob(tempJob);
            jobDispatchService.deleteJob(job);
            modelMap.put("str", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg : ", e.toString());
        }
        return modelMap;
    }

}