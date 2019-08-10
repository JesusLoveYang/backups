package com.bu.dao;

import com.bu.entity.MyJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobDao {

    /*
    新建任务
     */
    int insertJob(MyJob myJob);

    /*
    查询任务列表
     */
    List<MyJob> queryJobList(@Param("username") String username, @Param("initIndex") int initIndex,
                             @Param("pageSize") int pageSize);

    /*
    查询单个任务
     */
    MyJob queryJob(@Param("jobName") String jobname);
}
