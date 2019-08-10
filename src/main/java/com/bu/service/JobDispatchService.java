package com.bu.service;

import com.bu.entity.MyJob;

public interface JobDispatchService {
    /*
   向任务调度中添加定时任务
     */
    String addQuartzJob(MyJob myJob, Class cls);

    /*
    暂停任务
     */
    void pauseJob(MyJob myJob);

    /*
    恢复任务
     */
    void resumeJob(MyJob job);

    /*
    删除任务
     */
    String deleteJob(MyJob myJob);
}
