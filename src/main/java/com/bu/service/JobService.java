package com.bu.service;

import com.bu.entity.MyJob;

import java.util.List;

public interface JobService {

    /*
    新增任务
     */
    int insertJob(MyJob myJob);

    /*
    查询任务列表
     */
    List<MyJob> getJobList(String username, int pageIndex, int pageSize);

    /*
    查询任务
     */
    MyJob getJob(String jobname);
}
